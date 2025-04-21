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

    public boolean verificarEstado(Integer alumnoId) {
        // Obtener la instancia del microservicio registrado en Eureka
        ServiceInstance serviceInstance = loadBalancerClient.choose("MS-ALUMNOS");

        if (serviceInstance != null) {
            String baseUrl = serviceInstance.getUri().toString();
            String url = baseUrl + "/alumnos/" + alumnoId + "/estado"; // Endpoint de verificación de estado del alumno
            return restTemplate.getForObject(url, Boolean.class);
        }

        throw new RuntimeException("No se encontraron instancias del servicio MS-ALUMNOS en Eureka");
    }
}
