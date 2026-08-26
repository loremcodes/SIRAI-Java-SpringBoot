package com.assignment.sirai.service.impl;

import com.assignment.sirai.entity.EscuelaProfesional;
import com.assignment.sirai.repository.EscuelaProfesionalRepository;
import com.assignment.sirai.service.EscuelaProfesionalService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EscuelaServiImpl implements EscuelaProfesionalService {
    private final EscuelaProfesionalRepository repository;

    public EscuelaServiImpl(EscuelaProfesionalRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<EscuelaProfesional> listar() {
        return repository.findAll();
    }

    @Override
    public EscuelaProfesional guardar(EscuelaProfesional escuelaProfesional) {
        return repository.save(escuelaProfesional);
    }

    @Override
    public EscuelaProfesional buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}
