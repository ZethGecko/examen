package com.example.msmicroservicio2.service.impl;

import com.example.msmicroservicio2.dto.Cliente;
import com.netflix.discovery.DiscoveryClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ClienteService {
    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private RestTemplate restTemplate;

    public Cliente obtenerClientePorId(Integer clienteId) {
        // Obtener la instancia del microservicio registrado en Eureka
        ServiceInstance serviceInstance = loadBalancerClient.choose("MS-MICROSERVICIO1");

        if (serviceInstance != null) {
            String baseUrl = serviceInstance.getUri().toString();
            String url = baseUrl + "/clientes/" + clienteId;
            return restTemplate.getForObject(url, Cliente.class);
        }

        throw new RuntimeException("No se encontraron instancias del servicio MS-MICROSERVICIO1 en Eureka");
    }

}
