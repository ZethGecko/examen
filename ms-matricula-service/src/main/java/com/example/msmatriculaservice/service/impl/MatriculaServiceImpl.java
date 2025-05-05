package com.example.msmatriculaservice.service.impl;

import com.example.msmatriculaservice.dto.Curso;
import com.example.msmatriculaservice.dto.Estudiante;
import com.example.msmatriculaservice.entity.Matricula;
import com.example.msmatriculaservice.repository.MatriculaRepository;
import com.example.msmatriculaservice.service.MatriculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MatriculaServiceImpl implements MatriculaService {
    @Autowired
    MatriculaRepository matriculaRepository;

    @Autowired
    private CursoService cursoService;

    @Autowired
    private EstudianteService estudianteService;

    @Override
    public List<Matricula> listar() {
        return matriculaRepository.findAll();
    }

    @Override
    public Matricula guardar(Matricula matricula) {
        Estudiante estudiante = estudianteService.obtenerEstudiantePorId(matricula.getAlumnoId());
        if (estudiante == null || !"Activo".equals(estudiante.getEstado())) {
            throw new RuntimeException("El estudiante con ID " + matricula.getAlumnoId() + " no está activo.");
        }

        Curso curso = cursoService.obtenerCursoPorId(matricula.getCursoId());
        if (curso == null || curso.getCapacidad() <= 0) {
            throw new RuntimeException("El curso con ID " + matricula.getCursoId() + " ya ha alcanzado su capacidad máxima.");
        }

        // Registrar la matrícula
        matricula.setCursoNombre(curso.getNombre());
        matricula.setAlumnoNombre(estudiante.getNombre());
        matricula.setFechaMatricula(LocalDateTime.now());
        matricula.setEstado("Registrada");

        // Reducir la capacidad del curso después de la inscripción
        cursoService.reducirCapacidadCurso(matricula.getCursoId());

        return matriculaRepository.save(matricula);
    }


    @Override
    public Matricula actualizar(Matricula matricula) {
        return matriculaRepository.save(matricula);
    }

    @Override
    public Optional<Matricula> listarPorId(Integer id) {
        return matriculaRepository.findById(id);
    }

    @Override
    public void eliminarPorId(Integer id) {
        Matricula matricula = matriculaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Matrícula no encontrada con ID: " + id));

        // Restaurar la capacidad del curso
        cursoService.aumentarCapacidadCurso(matricula.getCursoId());

        matriculaRepository.deleteById(Long.valueOf(id));
    }
}
