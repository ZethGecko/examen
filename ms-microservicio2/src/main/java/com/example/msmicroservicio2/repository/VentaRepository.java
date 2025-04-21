package com.example.msmicroservicio2.repository;

import com.example.msmicroservicio2.entity.Venta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VentaRepository extends JpaRepository<Venta, Integer> {
    Optional<Venta> findByClienteId(Integer clienteId);
}
