package com.example.mscursoservice.repository;

import com.example.mscursoservice.entity.Cursos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CursoRepository extends JpaRepository<Cursos, Integer> {
    Optional<Object> findByNombre(String nombre);
}
