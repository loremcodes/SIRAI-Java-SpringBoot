package com.assignment.sirai.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "detalle_evaluacion")
public class DetalleEvaluacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "evaluacion_id", nullable = false)
    private Evaluacion evaluacion;

    @Column(name = "numero_pregunta", nullable = false)
    private Integer numPregunta;

    @Column(nullable = false)
    private Integer respuesta;

    public DetalleEvaluacion() {}

    public DetalleEvaluacion(Evaluacion evaluacion, Integer numPregunta, Integer respuesta) {
        this.evaluacion = evaluacion;
        this.numPregunta = numPregunta;
        this.respuesta = respuesta;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Evaluacion getEvaluacion() { return evaluacion; }
    public void setEvaluacion(Evaluacion evaluacion) { this.evaluacion = evaluacion; }

    public Integer getNumPregunta() { return numPregunta; }
    public void setNumPregunta(Integer numPregunta) { this.numPregunta = numPregunta; }

    public Integer getRespuesta() { return respuesta; }
    public void setRespuesta(Integer respuesta) { this.respuesta = respuesta; }
}