package com.example.msmatriculaservice.service.impl;

import com.example.msmatriculaservice.dto.Cursos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CursoService {
    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private RestTemplate restTemplate;

    public boolean verificarCapacidad(Integer cursoId) {
        // Obtener la instancia del microservicio registrado en Eureka
        ServiceInstance serviceInstance = loadBalancerClient.choose("MS-CURSOS");

        if (serviceInstance != null) {
            String baseUrl = serviceInstance.getUri().toString();
            String url = baseUrl + "/cursos/" + cursoId + "/verificar-capacidad"; // Endpoint de verificación de capacidad
            return restTemplate.getForObject(url, Boolean.class);
        }

        throw new RuntimeException("No se encontraron instancias del servicio MS-CURSOS en Eureka");
    }

    public String obtenerNombreCursoPorId(Integer cursoId) {
        ServiceInstance serviceInstance = loadBalancerClient.choose("MS-CURSOS");

        if (serviceInstance != null) {
            String baseUrl = serviceInstance.getUri().toString();
            String url = baseUrl + "/cursos/" + cursoId + "/nombre"; // Asegúrate de que este endpoint exista
            return restTemplate.getForObject(url, String.class);
        }

        throw new RuntimeException("No se encontraron instancias del servicio MS-CURSOS en Eureka");
    }

}
