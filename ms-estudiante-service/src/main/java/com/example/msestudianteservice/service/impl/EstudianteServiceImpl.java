package com.example.msestudianteservice.service.impl;

import com.example.msestudianteservice.entity.Estudiante;
import com.example.msestudianteservice.repository.EstudianteRepository;
import com.example.msestudianteservice.service.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstudianteServiceImpl implements EstudianteService {
    @Autowired
    EstudianteRepository estudianteRepository;

    @Override
    public List<Estudiante> listar() {
        return estudianteRepository.findAll();
    }

    @Override
    public Estudiante guardar(Estudiante estudiante) {
        // Verifica si el ID ya existe
        if (estudiante.getId() != null && estudianteRepository.existsById(estudiante.getId())) {
            throw new RuntimeException("El ID " + estudiante.getId() + " ya existe en la base de datos.");
        }

        // Verifica si algún campo único (como nombre) ya existe
        if (estudianteRepository.existsByNombre(estudiante.getNombre())) {
            throw new RuntimeException("El nombre '" + estudiante.getNombre() + "' ya está registrado.");
        }

        return estudianteRepository.save(estudiante);
    }
    @Override
    public Estudiante actualizar(Estudiante estudiante) {
        return estudianteRepository.save(estudiante);
    }

    @Override
    public Optional<Estudiante> listarPorId(Integer id) {
        return estudianteRepository.findById(id);
    }

    @Override
    public void eliminarPorId(Integer id) {
        estudianteRepository.deleteById(id);
    }

}
