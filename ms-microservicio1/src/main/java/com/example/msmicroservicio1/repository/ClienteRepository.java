package com.example.msmicroservicio1.repository;

import com.example.msmicroservicio1.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    Optional<Object> findByNombre(String nombre);
}
