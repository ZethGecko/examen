package com.example.msmatriculaservice.util;

import com.example.msmatriculaservice.dto.Curso;
import com.example.msmatriculaservice.dto.Estudiante;
import com.example.msmatriculaservice.entity.Matricula;
import com.example.msmatriculaservice.repository.MatriculaRepository;
import com.example.msmatriculaservice.service.EstudianteService;
import com.example.msmatriculaservice.service.impl.CursoService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
@Component
public class MatriculaSeeder {

    @Autowired
    private MatriculaRepository matriculaRepository;

    @Autowired
    private CursoService cursoService;

    @Autowired
    private EstudianteService estudianteService;

    @PostConstruct
    public void init() {
        try {
            // Verificar si ya existen datos en la tabla
            if (matriculaRepository.count() == 0) {

                // Crear una lista de matrículas con IDs de cursos y estudiantes
                int[][] matriculasData = {
                        {1, 1}, // Curso ID 1, Estudiante ID 101
                        {2, 2}, // Curso ID 2, Estudiante ID 102
                        {3, 3}, // Curso ID 3, Estudiante ID 103
                        {4, 4}, // Curso ID 4, Estudiante ID 104
                        {5, 5}  // Curso ID 5, Estudiante ID 105
                };

                for (int[] data : matriculasData) {
                    int cursoId = data[0];
                    int alumnoId = data[1];

                    Curso curso = cursoService.obtenerCursoPorId(cursoId);
                    Estudiante estudiante = estudianteService.obtenerEstudiantePorId(alumnoId);

                    if (curso != null && estudiante != null && "Activo".equals(estudiante.getEstado())) {
                        Matricula matricula = new Matricula();
                        matricula.setCursoId(cursoId);
                        matricula.setAlumnoId(alumnoId);
                        matricula.setCursoNombre(curso.getNombre());
                        matricula.setAlumnoNombre(estudiante.getNombre());
                        matricula.setFechaMatricula(LocalDateTime.now());
                        matricula.setEstado("Registrada");
                        matricula.setCiclo(1);

                        // Guardar la matrícula en la base de datos
                        matriculaRepository.save(matricula);

                        // Reducir la capacidad del curso
                        cursoService.reducirCapacidadCurso(cursoId);
                    }
                }
                System.out.println("Seeder ejecutado: matrículas inicializadas correctamente.");
            } else {
                System.out.println("Seeder omitido: ya existen matrículas en la tabla.");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error inicializando MatriculaSeeder: " + e.getMessage(), e);
        }
    }
}