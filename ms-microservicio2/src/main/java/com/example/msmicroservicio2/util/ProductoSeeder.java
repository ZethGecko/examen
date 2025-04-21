package com.example.msmicroservicio2.util;

import com.example.msmicroservicio2.entity.Producto;
import com.example.msmicroservicio2.repository.ProductoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Component
public class ProductoSeeder implements CommandLineRunner {
    private final ProductoRepository productoRepository;

    public ProductoSeeder(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    public void run(String... args) {
        // Verificar si ya existen productos para evitar duplicados
        if (productoRepository.count() == 0) {
            LocalDateTime fechaActual = LocalDateTime.now(ZoneId.systemDefault());

            Producto prod1 = new Producto(null, "Laptop", "Electrónica", "1200", fechaActual);
            Producto prod2 = new Producto(null, "Camiseta", "Ropa", "25", fechaActual);
            Producto prod3 = new Producto(null, "Silla ergonómica", "Hogar", "150", fechaActual);
            Producto prod4 = new Producto(null, "Muñeca Barbie", "Juguetes", "40", fechaActual);
            Producto prod5 = new Producto(null, "Libro de ciencia ficción", "Libros", "20", fechaActual);

            productoRepository.save(prod1);
            productoRepository.save(prod2);
            productoRepository.save(prod3);
            productoRepository.save(prod4);
            productoRepository.save(prod5);

            System.out.println("Datos de productos insertados correctamente.");
        } else {
            System.out.println("Los productos ya existen, no se insertaron datos.");
        }
    }
}
