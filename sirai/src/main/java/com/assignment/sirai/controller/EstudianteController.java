package com.assignment.sirai.controller;

import com.assignment.sirai.entity.Estudiante;
import com.assignment.sirai.service.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class EstudianteController {
    @Autowired
    private EstudianteService estudianteService;

    @GetMapping("/admin/estudiantes")
    public List<Estudiante> listar(){
        return estudianteService.listar();
    }

    @GetMapping("/admin/estudiantes/{id}")
    public Estudiante buscarPorId(@PathVariable Long id){
        return estudianteService.buscarPorId(id);
    }

    @PostMapping("/public/estudiantes")
    public Estudiante guardar(@RequestBody Estudiante estudiante){
        return estudianteService.guardar(estudiante);
    }
}
