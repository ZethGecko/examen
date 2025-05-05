package com.example.mscursoservice.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Cursos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String horarios;

    @Column(nullable = false)
    private Integer capacidad; // ✅ Asegurar que es Integer, NO String

    @Column(nullable = false)
    private Integer inscritos = 0;

    @Column(nullable = false)
    private Integer codigo;

    @Column(nullable = false)
    private String ciclo;

    @Column(nullable = false)
    private LocalDateTime fechaCreacion;

    // Getters y Setters corregidos
    public Integer getCapacidad() { return capacidad; } // ✅ Retorna Integer
    public void setCapacidad(Integer capacidad) { this.capacidad = capacidad; }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getHorarios() { return horarios; }
    public void setHorarios(String horarios) { this.horarios = horarios; }

    public Integer getInscritos() { return inscritos; }
    public void setInscritos(Integer inscritos) { this.inscritos = inscritos; }

    public Integer getCodigo() { return codigo; }
    public void setCodigo(Integer codigo) { this.codigo = codigo; }

    public String getCiclo() { return ciclo; }
    public void setCiclo(String ciclo) { this.ciclo = ciclo; }

    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(LocalDateTime fechaCreacion) { this.fechaCreacion = fechaCreacion; }

    @Override
    public String toString() {
        return "Cursos{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", horarios='" + horarios + '\'' +
                ", capacidad=" + capacidad +
                ", inscritos=" + inscritos +
                ", codigo=" + codigo +
                ", ciclo='" + ciclo + '\'' +
                ", fechaCreacion=" + fechaCreacion +
                '}';
    }

    public Cursos(Integer id, String nombre, String horarios, Integer capacidad, Integer inscritos, Integer codigo, String ciclo, LocalDateTime fechaCreacion) {
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
