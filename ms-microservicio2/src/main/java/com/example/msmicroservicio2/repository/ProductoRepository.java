package com.example.msmicroservicio2.repository;

import com.example.msmicroservicio2.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {
    Optional<Object> findByNombre(String nombre);
}
