package com.assignment.sirai.service;

import com.assignment.sirai.entity.Resultado;

import java.util.List;
import java.util.Map;

public interface ResultadoService {
    List<Resultado> listar();
    Resultado guardar(Resultado resultado);
    Resultado buscarPorId(Long id);

    String clasificarNivel(int puntaje);
    List<String> generarRecomendaciones(String nivel, int usoExcesivo, int dependencia, int impacto, int perdidaControl);
    Map<String, Integer> parsearRespuestas(String jsonRespuestas);
    int calcularPuntajeDimension(Map<String, Integer> respuestas, int inicio, int fin);
}

