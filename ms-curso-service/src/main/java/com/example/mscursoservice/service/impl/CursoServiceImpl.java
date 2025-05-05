package com.example.mscursoservice.service.impl;

import com.example.mscursoservice.entity.Cursos;
import com.example.mscursoservice.repository.CursoRepository;
import com.example.mscursoservice.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CursoServiceImpl implements CursoService {
    @Autowired
    CursoRepository cursoRepository;

    @Override
    public List<Cursos> listar() {
        return cursoRepository.findAll();
    }

    @Override
    public Cursos guardar(Cursos cursos) {
        return cursoRepository.save(cursos);
    }

    @Override
    public Cursos actualizar(Cursos cursos) { return cursoRepository.save(cursos); }

    @Override
    public Optional<Cursos> listarPorId(Integer id) {
        return cursoRepository.findById(id);
    }

    @Override
    public void eliminarPorId(Integer id) {
        cursoRepository.deleteById(id);
    }

//    Nuevo metodo para manejar la inscripción
    public Cursos inscribirEstudiante(Integer cursoId) {
        Cursos curso = cursoRepository.findById(cursoId)
                .orElseThrow(() -> new RuntimeException("Curso no encontrado con ID " + cursoId));

        // Cambiar curso.getCapacida() por curso.getCapacidad()
        int capacidadMaxima = curso.getCapacidad(); // No es necesario convertirlo de String a int

        if (curso.getInscritos() >= capacidadMaxima) {
            throw new RuntimeException("El curso '" + curso.getNombre() + "' ha alcanzado su capacidad máxima.");
        }

        // Incrementar el número de inscritos y reducir la capacidad del curso
        curso.setInscritos(curso.getInscritos() + 1);
        curso.setCapacidad(curso.getCapacidad() - 1);

        // Guardar los cambios en la base de datos
        return cursoRepository.save(curso);
    }


    @Override
    public String obtenerNombrePorId(Integer id) {
        Cursos curso = cursoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Curso no encontrado con ID: " + id));
        return curso.getNombre();
    }


}
