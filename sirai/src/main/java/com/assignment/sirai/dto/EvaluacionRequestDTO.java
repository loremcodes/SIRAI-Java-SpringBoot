package com.assignment.sirai.dto;

import java.util.Map;

public class EvaluacionRequestDTO {
    private Long estudianteId;
    private Map<String, Integer> respuestas;

    public Long getEstudianteId() { return estudianteId; }
    public void setEstudianteId(Long estudianteId) { this.estudianteId = estudianteId; }

    public Map<String, Integer> getRespuestas() { return respuestas; }
    public void setRespuestas(Map<String, Integer> respuestas) { this.respuestas = respuestas; }
}