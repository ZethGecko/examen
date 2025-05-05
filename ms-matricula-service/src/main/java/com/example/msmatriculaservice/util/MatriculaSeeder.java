package com.example.msmatriculaservice.util;

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
            if (matriculaRepository.count() == 0) {

                int[][] matriculasData = {
                        {1, 1}, {2, 2}, {3, 3}, {4, 4}, {5, 5}
                };

                for (int[] data : matriculasData) {
                    int cursoId = data[0];
                    int alumnoId = data[1];

                    Matricula matricula = new Matricula();
                    matricula.setCursoId(cursoId);
                    matricula.setAlumnoId(alumnoId);
                    matricula.setFechaMatricula(LocalDateTime.now());
                    matricula.setEstado("Registrada");
                    matricula.setCiclo(1);

                    // ✅ No guardamos el nombre del curso ni del alumno en la base de datos
                    matriculaRepository.save(matricula);
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