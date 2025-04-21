package com.example.msmatriculaservice.repository;

import com.example.msmatriculaservice.entity.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MatriculaRepository extends JpaRepository<Matricula, Integer> {
    Optional<Object> findByEstado(String estado);
}
