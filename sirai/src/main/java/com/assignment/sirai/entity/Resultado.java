package com.assignment.sirai.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "resultado")
public class Resultado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "evaluacion_id", nullable = false, unique = true)
    @JsonIgnoreProperties("resultado")
    private Evaluacion evaluacion;

    @Column(name = "uso_excesivo_internet", nullable = false)
    private Integer usoExcesivoInternet;

    @Column(name = "dependencia_emocional", nullable = false)
    private Integer dependenciaEmocional;

    @Column(name = "impacto_academico", nullable = false)
    private Integer impactoAcademico;

    @Column(name = "perdida_control", nullable = false)
    private Integer perdidaControl;

    @Column(name = "puntaje_total", nullable = false)
    private Integer puntajeTotal;

    @Column(name = "nivel_riesgo", nullable = false)
    private String nivelRiesgo;

    @Column(nullable = false, length = 1000)
    private String recomendaciones;

    public Resultado() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Evaluacion getEvaluacion() { return evaluacion; }
    public void setEvaluacion(Evaluacion evaluacion) { this.evaluacion = evaluacion; }

    public Integer getUsoExcesivoInternet() { return usoExcesivoInternet; }
    public void setUsoExcesivoInternet(Integer usoExcesivoInternet) { this.usoExcesivoInternet = usoExcesivoInternet; }

    public Integer getDependenciaEmocional() { return dependenciaEmocional; }
    public void setDependenciaEmocional(Integer dependenciaEmocional) { this.dependenciaEmocional = dependenciaEmocional; }

    public Integer getImpactoAcademico() { return impactoAcademico; }
    public void setImpactoAcademico(Integer impactoAcademico) { this.impactoAcademico = impactoAcademico; }

    public Integer getPerdidaControl() { return perdidaControl; }
    public void setPerdidaControl(Integer perdidaControl) { this.perdidaControl = perdidaControl; }

    public Integer getPuntajeTotal() { return puntajeTotal; }
    public void setPuntajeTotal(Integer puntajeTotal) { this.puntajeTotal = puntajeTotal; }

    public String getNivelRiesgo() { return nivelRiesgo; }
    public void setNivelRiesgo(String nivelRiesgo) { this.nivelRiesgo = nivelRiesgo; }

    public String getRecomendaciones() { return recomendaciones; }
    public void setRecomendaciones(String recomendaciones) { this.recomendaciones = recomendaciones; }
}