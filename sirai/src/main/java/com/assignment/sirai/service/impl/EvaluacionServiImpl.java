package com.assignment.sirai.service.impl;

import com.assignment.sirai.dto.EvaluacionRequestDTO;
import com.assignment.sirai.entity.*;
import com.assignment.sirai.repository.DetalleEvaluacionRepository;
import com.assignment.sirai.repository.EvaluacionRepository;
import com.assignment.sirai.service.EstudianteService;
import com.assignment.sirai.service.EvaluacionService;
import com.assignment.sirai.service.ResultadoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class EvaluacionServiImpl implements EvaluacionService {

    private final EvaluacionRepository evaluacionRepository;
    private final DetalleEvaluacionRepository detalleRepository;
    private final EstudianteService estudianteService;
    private final ResultadoService resultadoService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public EvaluacionServiImpl(EvaluacionRepository evaluacionRepository,
                               DetalleEvaluacionRepository detalleRepository,
                               EstudianteService estudianteService,
                               ResultadoService resultadoService) {
        this.evaluacionRepository = evaluacionRepository;
        this.detalleRepository = detalleRepository;
        this.estudianteService = estudianteService;
        this.resultadoService = resultadoService;
    }

    @Override
    @Transactional
    public Resultado procesarYGuardarEvaluacion(EvaluacionRequestDTO dto) {
        // 1. Buscar al estudiante
        Estudiante estudiante = estudianteService.buscarPorId(dto.getEstudianteId());
        if (estudiante == null) {
            throw new RuntimeException("Estudiante no encontrado con ID: " + dto.getEstudianteId());
        }

        // 2. Guardar la Cabecera (Evaluación)
        Evaluacion evaluacion = new Evaluacion(estudiante, LocalDateTime.now());
        Evaluacion evaluacionGuardada = evaluacionRepository.save(evaluacion);

        // 3. Guardar el Detalle (20 respuestas)
        Map<String, Integer> respuestas = dto.getRespuestas();
        List<DetalleEvaluacion> detalles = new ArrayList<>();

        for (int i = 1; i <= 20; i++) {
            Integer valorRespuesta = respuestas.getOrDefault("p_" + i, 0);
            detalles.add(new DetalleEvaluacion(evaluacionGuardada, i, valorRespuesta));
        }
        detalleRepository.saveAll(detalles);

        // 4. Calcular los Puntajes usando el ResultadoService
        int usoExcesivo = resultadoService.calcularPuntajeDimension(respuestas, 1, 5);
        int dependencia = resultadoService.calcularPuntajeDimension(respuestas, 6, 10);
        int impacto = resultadoService.calcularPuntajeDimension(respuestas, 11, 15);
        int perdidaControl = resultadoService.calcularPuntajeDimension(respuestas, 16, 20);

        int total = usoExcesivo + dependencia + impacto + perdidaControl;
        String nivelRiesgo = resultadoService.clasificarNivel(total);
        List<String> recomendacionesList = resultadoService.generarRecomendaciones(
                nivelRiesgo, usoExcesivo, dependencia, impacto, perdidaControl
        );

        // 5. Guardar y Retornar el Resultado final asociado a esta Evaluación
        Resultado resultado = new Resultado();
        resultado.setEvaluacion(evaluacionGuardada);
        resultado.setUsoExcesivoInternet(usoExcesivo);
        resultado.setDependenciaEmocional(dependencia);
        resultado.setImpactoAcademico(impacto);
        resultado.setPerdidaControl(perdidaControl);
        resultado.setPuntajeTotal(total);
        resultado.setNivelRiesgo(nivelRiesgo);

        try {
            resultado.setRecomendaciones(objectMapper.writeValueAsString(recomendacionesList));
        } catch (Exception e) {
            resultado.setRecomendaciones("[]");
        }

        return resultadoService.guardar(resultado);
    }

    @Override
    public List<Evaluacion> listar() {
        return evaluacionRepository.findAll();
    }

    @Override
    public Evaluacion buscarPorId(Long id) {
        return evaluacionRepository.findById(id).orElse(null);
    }
}