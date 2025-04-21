package com.example.msestudianteservice.repository;

import com.example.msestudianteservice.entity.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface EstudianteRepository extends JpaRepository<Estudiante, Integer> {
    Optional<Object> findByNombre(String nombre);

    boolean existsByNombre(String nombre);
}
