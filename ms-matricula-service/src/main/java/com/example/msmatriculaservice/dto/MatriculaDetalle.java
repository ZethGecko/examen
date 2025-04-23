package com.example.msmatriculaservice.dto;

import java.time.LocalDateTime;

public class MatriculaDetalle {
    private Integer id;
    private String estado;
    private String cursoNombre; // Nombre del curso
    private String alumnoNombre; // Nombre del estudiante
    private Integer ciclo;
    private LocalDateTime fechaMatricula;

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

    public String getCursoNombre() {
        return cursoNombre;
    }

    public void setCursoNombre(String cursoNombre) {
        this.cursoNombre = cursoNombre;
    }

    public String getAlumnoNombre() {
        return alumnoNombre;
    }

    public void setAlumnoNombre(String alumnoNombre) {
        this.alumnoNombre = alumnoNombre;
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

    public MatriculaDetalle() {
    }

    public MatriculaDetalle(Integer id, String estado, String cursoNombre, String alumnoNombre, Integer ciclo, LocalDateTime fechaMatricula) {
        this.id = id;
        this.estado = estado;
        this.cursoNombre = cursoNombre;
        this.alumnoNombre = alumnoNombre;
        this.ciclo = ciclo;
        this.fechaMatricula = fechaMatricula;
    }

    @Override
    public String toString() {
        return "MatriculaDetalle{" +
                "id=" + id +
                ", estado='" + estado + '\'' +
                ", cursoNombre='" + cursoNombre + '\'' +
                ", alumnoNombre='" + alumnoNombre + '\'' +
                ", ciclo=" + ciclo +
                ", fechaMatricula=" + fechaMatricula +
                '}';
    }
}
