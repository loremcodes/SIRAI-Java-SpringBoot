package com.assignment.sirai.service;

import com.assignment.sirai.entity.EscuelaProfesional;
import org.springframework.stereotype.Service;

import java.util.List;

public interface EscuelaProfesionalService {
    List<EscuelaProfesional> listar();
    EscuelaProfesional guardar(EscuelaProfesional escuelaProfesional);
    EscuelaProfesional buscarPorId(Long id);
    void eliminar(Long id);
}
