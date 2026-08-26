package com.assignment.sirai.service.impl;

import com.assignment.sirai.entity.Estudiante;
import com.assignment.sirai.repository.EstudianteRepository;
import com.assignment.sirai.service.EstudianteService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EstudianteServiImpl implements EstudianteService {
    private final EstudianteRepository repository;

    public EstudianteServiImpl(EstudianteRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Estudiante> listar() {
        return repository.findAll();
    }

    @Override
    public Estudiante guardar(Estudiante estudiante) {
        return repository.save(estudiante);
    }

    @Override
    public Estudiante buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }
}
