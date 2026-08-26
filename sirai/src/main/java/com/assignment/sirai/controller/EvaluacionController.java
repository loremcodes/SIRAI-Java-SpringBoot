package com.assignment.sirai.controller;

import com.assignment.sirai.dto.EvaluacionRequestDTO;
import com.assignment.sirai.entity.Evaluacion;
import com.assignment.sirai.entity.Resultado;
import com.assignment.sirai.service.EvaluacionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class EvaluacionController {

    private final EvaluacionService evaluacionService;

    public EvaluacionController(EvaluacionService evaluacionService) {
        this.evaluacionService = evaluacionService;
    }
    @GetMapping("admin/evaluaciones")
    public List<Evaluacion> lista(){
        return evaluacionService.listar();
    }

    @GetMapping("admin/evaluaciones/{id}")
    public Evaluacion buscarPorId(@PathVariable Long id){
        return evaluacionService.buscarPorId(id);
    }

    @PostMapping("evaluacion/guardar")
    public ResponseEntity<Resultado> guardarEvaluacion(@RequestBody EvaluacionRequestDTO dto) {
        Resultado resultadoCalculado = evaluacionService.procesarYGuardarEvaluacion(dto);
        return ResponseEntity.ok(resultadoCalculado);
    }
}