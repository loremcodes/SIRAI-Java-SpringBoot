package com.assignment.sirai.service;

import com.assignment.sirai.entity.Estudiante;
import org.springframework.stereotype.Service;

import java.util.List;

public interface EstudianteService {
    List<Estudiante> listar();
    Estudiante guardar(Estudiante estudiante);
    Estudiante buscarPorId(Long id);
}
