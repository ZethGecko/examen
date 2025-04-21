package com.example.msmatriculaservice.service.impl;

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
        // Verificar la capacidad del curso a través de `cursoService`
        boolean capacidadDisponible = cursoService.verificarCapacidad(matricula.getCursoId());
        if (!capacidadDisponible) {
            throw new RuntimeException("El curso con ID " + matricula.getCursoId() + " ha alcanzado su capacidad máxima.");
        }

        // Verificar el estado del estudiante a través de `estudianteService`
        boolean estudianteActivo = estudianteService.verificarEstado(matricula.getAlumnoId());
        if (!estudianteActivo) {
            throw new RuntimeException("El estudiante con ID " + matricula.getAlumnoId() + " no está activo.");
        }

        // Registrar la matrícula
        matricula.setFechaMatricula(LocalDateTime.now());
        matricula.setEstado("Registrada"); // Estado inicial
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
        matriculaRepository.deleteById(id);
    }

}