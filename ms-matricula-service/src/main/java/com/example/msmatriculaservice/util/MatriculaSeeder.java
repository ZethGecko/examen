package com.example.msmatriculaservice.util;

import com.example.msmatriculaservice.entity.Matricula;
import com.example.msmatriculaservice.repository.MatriculaRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class MatriculaSeeder {
    @Autowired
    private MatriculaRepository matriculaRepository;

    @PostConstruct
    public void init() {
        // Verificar si ya existen registros de matrícula antes de insertar nuevos datos
        if (!matriculaRepository.existsById(1)) {
            matriculaRepository.save(new Matricula(1, "Registrada", 101, 201, 1, LocalDateTime.now()));
        }

        if (!matriculaRepository.existsById(2)) {
            matriculaRepository.save(new Matricula(2, "Registrada", 102, 202, 1, LocalDateTime.now()));
        }

        if (!matriculaRepository.existsById(3)) {
            matriculaRepository.save(new Matricula(3, "Registrada", 103, 203, 2, LocalDateTime.now()));
        }

        if (!matriculaRepository.existsById(4)) {
            matriculaRepository.save(new Matricula(4, "Registrada", 104, 204, 2, LocalDateTime.now()));
        }

        if (!matriculaRepository.existsById(5)) {
            matriculaRepository.save(new Matricula(5, "Registrada", 105, 205, 3, LocalDateTime.now()));
        }

        System.out.println("Seeder ejecutado: datos de matrícula inicializados correctamente.");
    }
}
