package com.example.msmicroservicio2.util;

import com.example.msmicroservicio2.entity.Producto;
import com.example.msmicroservicio2.entity.Venta;
import com.example.msmicroservicio2.repository.ProductoRepository;
import com.example.msmicroservicio2.repository.VentaRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Component
public class VentaSeeder {
    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @PostConstruct
    public void init() {
        // Verificar si ya existen datos en la tabla ventas
        if (ventaRepository.count() > 0) {
            System.out.println("Los datos de ventas ya están inicializados. No se agregarán duplicados.");
            return;
        }

        // Crear productos de prueba (si no existen)
        Producto producto1 = productoRepository.save(new Producto(null, "Producto 1", "Tipo A", "20.00", LocalDateTime.now()));
        Producto producto2 = productoRepository.save(new Producto(null, "Producto 2", "Tipo B", "40.00", LocalDateTime.now()));

        // Crear ventas relacionadas con clientes y productos
        Venta venta1 = new Venta();
        venta1.setProducto(producto1);
        venta1.setClienteId(1); // Referencia lógica al cliente Pérez
        venta1.setCantidad(2);
        venta1.setFechaVenta(LocalDateTime.now());

        Venta venta2 = new Venta();
        venta2.setProducto(producto2);
        venta2.setClienteId(2); // Referencia lógica al cliente Gómez
        venta2.setCantidad(1);
        venta2.setFechaVenta(LocalDateTime.now());

        Venta venta3 = new Venta();
        venta3.setProducto(producto1);
        venta3.setClienteId(3); // Referencia lógica al cliente Fernández
        venta3.setCantidad(5);
        venta3.setFechaVenta(LocalDateTime.now());

        // Guardar las ventas en la base de datos
        ventaRepository.saveAll(Arrays.asList(venta1, venta2, venta3));

        System.out.println("Datos de ventas inicializados correctamente.");
    }
}
