package com.assignment.sirai.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "estudiante")
public class Estudiante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 100)
    private String nombres;
    @Column(nullable = false, length = 100)
    private String apellidos;
    @Column(nullable = false, length = 10)
    private String sexo;
    @Column(nullable = false)
    private int edad;
    @ManyToOne
    @JoinColumn(name="escuela_id")
    private EscuelaProfesional escuelaProfesional;
    @Column(name = "ciclo_academico",nullable = false, length = 4)
    private String cicloAcademico;
    @Column(name = "promedio_ponderado",nullable = false)
    private Double promedioPonderado;

    public Estudiante(){}
    public Estudiante(Long id, String nombres, String apellidos, String sexo, int edad, EscuelaProfesional escuelaProfesional, String cicloAcademico, Double promedioPonderado) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.sexo = sexo;
        this.edad = edad;
        this.escuelaProfesional = escuelaProfesional;
        this.cicloAcademico = cicloAcademico;
        this.promedioPonderado = promedioPonderado;
    }

    public Long getId() {
        return id;
    }

    public String getNombres() {
        return nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getSexo() {
        return sexo;
    }

    public int getEdad() {
        return edad;
    }

    public EscuelaProfesional getEscuelaProfesional() {
        return escuelaProfesional;
    }

    public String getCicloAcademico() {
        return cicloAcademico;
    }

    public Double getPromedioPonderado() {
        return promedioPonderado;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setEscuelaProfesional(EscuelaProfesional escuelaProfesional) {
        this.escuelaProfesional = escuelaProfesional;
    }

    public void setCicloAcademico(String cicloAcademico) {
        this.cicloAcademico = cicloAcademico;
    }

    public void setPromedioPonderado(Double promedioPonderado) {
        this.promedioPonderado = promedioPonderado;
    }
}
