package com.assignment.sirai.controller;

import com.assignment.sirai.entity.EscuelaProfesional;
import com.assignment.sirai.service.EscuelaProfesionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class EscuelaController {
    @Autowired
    private EscuelaProfesionalService escuelaService;

    @GetMapping("/public/escuelas-profesionales")
    public List<EscuelaProfesional> listar(){
        return escuelaService.listar();
    }

    @GetMapping("/admin/escuelas-profesionales/{id}")
    public EscuelaProfesional buscarPorId(@PathVariable Long id){
        return escuelaService.buscarPorId(id);
    }

    @PostMapping("/admin/escuela-profesional/nuevo")
    public EscuelaProfesional guardar(@RequestBody EscuelaProfesional escuelaProfesional){
        return escuelaService.guardar(escuelaProfesional);
    }

    @PutMapping("/admin/escuela-profesionale/editar/{id}")
    public EscuelaProfesional editar(@PathVariable Long id, @RequestBody EscuelaProfesional escuelaProfesional){
        escuelaProfesional.setId(id);
        return escuelaService.guardar(escuelaProfesional);
    }

    @DeleteMapping("/admin/escuela-profesional/eliminar/{id}")
    public void eliminar(@PathVariable Long id){
        escuelaService.eliminar(id);
    }
}
