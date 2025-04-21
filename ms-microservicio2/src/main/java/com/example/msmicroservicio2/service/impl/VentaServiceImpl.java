package com.example.msmicroservicio2.service.impl;

import com.example.msmicroservicio2.dto.Cliente;
import com.example.msmicroservicio2.entity.Venta;
import com.example.msmicroservicio2.repository.VentaRepository;
import com.example.msmicroservicio2.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VentaServiceImpl implements VentaService {
    @Autowired
    VentaRepository ventaRepository;

    @Autowired
    private ClienteService clienteService;

    @Override
    public List<Venta> listar() {
        return ventaRepository.findAll();
    }

    @Override
    public Venta guardar(Venta venta) {
        // Verificar si el cliente existe en el microservicio 1
        Cliente cliente = clienteService.obtenerClientePorId(venta.getClienteId());
        if (cliente == null) {
            throw new RuntimeException("El cliente con ID " + venta.getClienteId() + " no existe.");
        }

        // Guardar la venta si el cliente existe
        return ventaRepository.save(venta);
    }
    @Override
    public Venta actualizar(Venta venta) {
        return ventaRepository.save(venta);
    }

    @Override
    public Optional<Venta> listarPorId(Integer id) {
        return ventaRepository.findById(id);
    }

    @Override
    public void eliminarPorId(Integer id) {
        ventaRepository.deleteById(id);
    }

}
