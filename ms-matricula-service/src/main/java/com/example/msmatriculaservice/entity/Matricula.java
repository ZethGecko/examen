package com.example.msmatriculaservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Matricula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String estado;
    private Integer cursoId; // Foreign key simulada del curso
    private Integer alumnoId; // Foreign key simulada del alumno

    private Integer ciclo; // Ciclo de la matrícula
    private LocalDateTime fechaMatricula; // Fecha de matrícula

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getCursoId() {
        return cursoId;
    }

    public void setCursoId(Integer cursoId) {
        this.cursoId = cursoId;
    }

    public Integer getAlumnoId() {
        return alumnoId;
    }

    public void setAlumnoId(Integer alumnoId) {
        this.alumnoId = alumnoId;
    }

    public Integer getCiclo() {
        return ciclo;
    }

    public void setCiclo(Integer ciclo) {
        this.ciclo = ciclo;
    }

    public LocalDateTime getFechaMatricula() {
        return fechaMatricula;
    }

    public void setFechaMatricula(LocalDateTime fechaMatricula) {
        this.fechaMatricula = fechaMatricula;
    }

    public Matricula(Integer id, String estado, Integer cursoId, Integer alumnoId, Integer ciclo, LocalDateTime fechaMatricula) {
        this.id = id;
        this.estado = estado;
        this.cursoId = cursoId;
        this.alumnoId = alumnoId;
        this.ciclo = ciclo;
        this.fechaMatricula = fechaMatricula;
    }

    public Matricula() {
    }

    @Override
    public String toString() {
        return "Matricula{" +
                "id=" + id +
                ", estado='" + estado + '\'' +
                ", cursoId=" + cursoId +
                ", alumnoId=" + alumnoId +
                ", ciclo=" + ciclo +
                ", fechaMatricula=" + fechaMatricula +
                '}';
    }
}
