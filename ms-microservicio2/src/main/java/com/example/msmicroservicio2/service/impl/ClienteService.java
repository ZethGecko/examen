package com.example.msmicroservicio2.service.impl;

import com.example.msmicroservicio2.dto.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ClienteService {
    @Autowired
    private RestTemplate restTemplate;

    public Cliente obtenerClientePorId(Integer clienteId) {
        String url = "http://localhost:8088/clientes" + clienteId; // URL del microservicio 1
        return restTemplate.getForObject(url, Cliente.class);
    }
}
