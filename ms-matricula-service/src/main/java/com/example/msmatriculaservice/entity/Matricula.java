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
}
