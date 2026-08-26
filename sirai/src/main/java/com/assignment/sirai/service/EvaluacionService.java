package com.assignment.sirai.service;

import com.assignment.sirai.dto.EvaluacionRequestDTO;
import com.assignment.sirai.entity.Evaluacion;
import com.assignment.sirai.entity.Resultado;

import java.util.List;

public interface EvaluacionService {
    Resultado procesarYGuardarEvaluacion(EvaluacionRequestDTO dto);
    List<Evaluacion> listar();
    Evaluacion buscarPorId(Long id);
}