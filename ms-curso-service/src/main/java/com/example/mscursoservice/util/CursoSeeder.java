package com.example.mscursoservice.util;

import com.example.mscursoservice.entity.Cursos;
import com.example.mscursoservice.repository.CursoRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CursoSeeder {

    @Autowired
    private CursoRepository cursoRepository;

    @PostConstruct
    public void init() {
        // Verificar y agregar datos iniciales solo si no existen registros con los IDs especificados
        if (!cursoRepository.existsById(1)) {
            cursoRepository.save(new Cursos(1, "Matemáticas", "Lunes a Viernes 9-11AM", "10", 0, 1001, "Ciclo 1", LocalDateTime.now()));
        }

        if (!cursoRepository.existsById(2)) {
            cursoRepository.save(new Cursos(2, "Física", "Martes y Jueves 2-4PM", "10", 0, 1002, "Ciclo 2", LocalDateTime.now()));
        }

        if (!cursoRepository.existsById(3)) {
            cursoRepository.save(new Cursos(3, "Química", "Lunes y Miércoles 11-1PM", "10", 0, 1003, "Ciclo 3", LocalDateTime.now()));
        }

        if (!cursoRepository.existsById(4)) {
            cursoRepository.save(new Cursos(4, "Programación", "Viernes 3-5PM", "10", 0, 1004, "Ciclo 4", LocalDateTime.now()));
        }

        if (!cursoRepository.existsById(5)) {
            cursoRepository.save(new Cursos(5, "Historia", "Sábado 10-12PM", "10", 0, 1005, "Ciclo 5", LocalDateTime.now()));
        }

        System.out.println("Seeder ejecutado: datos de cursos inicializados correctamente.");
    }
}
