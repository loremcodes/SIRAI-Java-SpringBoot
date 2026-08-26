package com.assignment.sirai.service.impl;

import com.assignment.sirai.entity.Resultado;
import com.assignment.sirai.repository.ResultadoRepository;
import com.assignment.sirai.service.ResultadoService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ResultadoServiImpl implements ResultadoService {
    private final ResultadoRepository repository;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public ResultadoServiImpl(ResultadoRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Resultado> listar() {
        return repository.findAll();
    }

    @Override
    public Resultado guardar(Resultado resultado) {
        return repository.save(resultado);
    }

    @Override
    public Resultado buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public String clasificarNivel(int puntaje) {
        if (puntaje >= 20 && puntaje <= 46) {
            return "BAJO";
        } else if (puntaje >= 47 && puntaje <= 73) {
            return "MODERADO";
        } else if (puntaje >= 74 && puntaje <= 100) {
            return "ALTO";
        } else {
            return "NO VÁLIDO";
        }
    }

    @Override
    public List<String> generarRecomendaciones(String nivel, int usoExcesivo, int dependencia, int impacto, int perdidaControl) {
        List<String> recomendaciones = new ArrayList<>();

        if ("BAJO".equals(nivel)) {
            recomendaciones.add("El estudiante presenta uso controlado de Internet. Se recomienda mantener hábitos digitales saludables.");
        } else if ("MODERADO".equals(nivel)) {
            recomendaciones.add("El estudiante presenta riesgo moderado de uso problemático de Internet. Se recomienda regular horarios de conexión.");
        } else if ("ALTO".equals(nivel)) {
            recomendaciones.add("El estudiante presenta alto riesgo de adicción a Internet. Se recomienda orientación psicológica, tutoría académica y seguimiento personalizado.");
        }

        if (usoExcesivo >= 18) {
            recomendaciones.add("Reducir el tiempo diario de conexión y establecer límites de uso.");
        }
        if (dependencia >= 18) {
            recomendaciones.add("Trabajar estrategias de regulación emocional sin depender del Internet.");
        }
        if (impacto >= 18) {
            recomendaciones.add("Organizar horarios de estudio y evitar el uso de Internet durante clases o tareas.");
        }
        if (perdidaControl >= 18) {
            recomendaciones.add("Usar alarmas, bloqueadores de aplicaciones y cronogramas digitales.");
        }

        return recomendaciones;
    }

    @Override
    public Map<String, Integer> parsearRespuestas(String jsonRespuestas) {
        try {
            return objectMapper.readValue(jsonRespuestas, new TypeReference<Map<String, Integer>>() {});
        } catch (Exception e) {
            return new HashMap<>();
        }
    }

    @Override
    public int calcularPuntajeDimension(Map<String, Integer> respuestas, int inicio, int fin) {
        int suma = 0;
        for (int i = inicio; i <= fin; i++) {
            suma += respuestas.getOrDefault("p_" + i, 0);
        }
        return suma;
    }
}
