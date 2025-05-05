package com.example.mscursoservice.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Cursos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private String horarios;
    private String capacidad; // Capacidad máxima del curso
    private Integer inscritos = 0; // Inicialmente, ningún estudiante está inscrito
    private Integer codigo;
    private String ciclo;
    private LocalDateTime fechaCreacion;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getHorarios() {
        return horarios;
    }

    public void setHorarios(String horarios) {
        this.horarios = horarios;
    }

    public String getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(String capacidad) {
        this.capacidad = capacidad;
    }

    public Integer getInscritos() {
        return inscritos;
    }

    public void setInscritos(Integer inscritos) {
        this.inscritos = inscritos;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getCiclo() {
        return ciclo;
    }

    public void setCiclo(String ciclo) {
        this.ciclo = ciclo;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Cursos() {
    }

    @Override
    public String toString() {
        return "Cursos{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", horarios='" + horarios + '\'' +
                ", capacidad='" + capacidad + '\'' +
                ", inscritos=" + inscritos +
                ", codigo=" + codigo +
                ", ciclo='" + ciclo + '\'' +
                ", fechaCreacion=" + fechaCreacion +
                '}';
    }

    public Cursos(Integer id, String nombre, String horarios, String capacidad, Integer inscritos, Integer codigo, String ciclo, LocalDateTime fechaCreacion) {
        this.id = id;
        this.nombre = nombre;
        this.horarios = horarios;
        this.capacidad = capacidad;
        this.inscritos = inscritos;
        this.codigo = codigo;
        this.ciclo = ciclo;
        this.fechaCreacion = fechaCreacion;
    }
}
