package com.example.msmatriculaservice.service.impl;

import com.example.msmatriculaservice.dto.Estudiante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EstudianteService {
    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private RestTemplate restTemplate;

    public Estudiante obtenerEstudiantePorId(Integer alumnoId) {
        ServiceInstance serviceInstance = loadBalancerClient.choose("ms-estudiante-service");

        if (serviceInstance != null) {
            String baseUrl = serviceInstance.getUri().toString();
            String url = baseUrl + "/alumnos/" + alumnoId;

            try {
                return restTemplate.getForObject(url, Estudiante.class);
            } catch (Exception e) {
                throw new RuntimeException("Error al obtener estudiante con ID " + alumnoId + ": " + e.getMessage());
            }
        }

        throw new RuntimeException("No se encontraron instancias del servicio ms-estudiante-service en Eureka.");
    }
}