package com.example.msmicroservicio1.service;

import com.example.msmicroservicio1.entity.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteService {
    public List<Cliente> listar();
    public Cliente guardar(Cliente cliente);
    public Cliente actualizar(Cliente Cliente);
    public Optional<Cliente> listarPorId(Integer id);
    public void eliminarPorId(Integer id);
}
