package com.assignment.sirai.controller;

import com.assignment.sirai.entity.Evaluacion;
import com.assignment.sirai.entity.Resultado;
import com.assignment.sirai.service.ResultadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ResultadoController {
    @Autowired
    private ResultadoService resultadoService;

    @GetMapping("/admin/resultados/evaluacion/{evaluacion_id}")
    public Resultado buscarPorId(Long evaluacion_id){
        return resultadoService.buscarPorId(evaluacion_id);
    }

    @GetMapping("/admin/resultados")
    public List<Resultado> listarTodos() {
        return resultadoService.listar();
    }
}
