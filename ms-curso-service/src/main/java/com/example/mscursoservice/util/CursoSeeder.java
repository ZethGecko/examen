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
        try {
            // Solo ejecutar si la base de datos está vacía
            if (cursoRepository.count() == 0) {
                cursoRepository.save(new Cursos(null, "Matemáticas", "Lunes a Viernes 9-11AM", 2, 0, 1001, "Ciclo 1", LocalDateTime.now()));
                cursoRepository.save(new Cursos(null, "Física", "Martes y Jueves 2-4PM", 3, 0, 1002, "Ciclo 2", LocalDateTime.now()));
                cursoRepository.save(new Cursos(null, "Química", "Lunes y Miércoles 11-1PM", 10, 0, 1003, "Ciclo 3", LocalDateTime.now()));
                cursoRepository.save(new Cursos(null, "Programación", "Viernes 3-5PM", 10, 0, 1004, "Ciclo 4", LocalDateTime.now()));
                cursoRepository.save(new Cursos(null, "Historia", "Sábado 10-12PM", 10, 0, 1005, "Ciclo 5", LocalDateTime.now()));

                System.out.println("Seeder ejecutado: datos de cursos inicializados correctamente.");
            } else {
                System.out.println("Seeder omitido: ya existen datos en la tabla cursos.");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error inicializando CursoSeeder: " + e.getMessage(), e);
        }
    }
}
