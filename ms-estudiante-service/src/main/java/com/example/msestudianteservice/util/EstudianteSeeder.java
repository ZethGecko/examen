package com.example.msestudianteservice.util;

import com.example.msestudianteservice.entity.Estudiante;
import com.example.msestudianteservice.repository.EstudianteRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class EstudianteSeeder {

    @Autowired
    private EstudianteRepository estudianteRepository;

    @PostConstruct
    public void init() {
        // Evitar duplicados revisando si los registros ya existen
        if (!estudianteRepository.existsById(1)) {
            estudianteRepository.save(new Estudiante(1, "Juan Pérez", "Ingeniería", "Activo", "Ciclo 1", LocalDateTime.now()));
        }

        if (!estudianteRepository.existsById(2)) {
            estudianteRepository.save(new Estudiante(2, "María Gómez", "Administración", "Activo", "Ciclo 3", LocalDateTime.now()));
        }

        if (!estudianteRepository.existsById(3)) {
            estudianteRepository.save(new Estudiante(3, "Carlos Fernández", "Derecho", "Suspendido", "Ciclo 2", LocalDateTime.now()));
        }

        if (!estudianteRepository.existsById(4)) {
            estudianteRepository.save(new Estudiante(4, "Ana Ramírez", "Arquitectura", "Activo", "Ciclo 4", LocalDateTime.now()));
        }

        if (!estudianteRepository.existsById(5)) {
            estudianteRepository.save(new Estudiante(5, "Luis Torres", "Medicina", "Activo", "Ciclo 6", LocalDateTime.now()));
        }

        System.out.println("Datos de estudiantes inicializados correctamente.");
    }
}
