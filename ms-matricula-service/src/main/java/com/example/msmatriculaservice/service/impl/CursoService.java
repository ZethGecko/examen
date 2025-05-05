package com.example.msmatriculaservice.service.impl;

import com.example.msmatriculaservice.dto.Curso;
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

    public void decrementarInscritos(Integer cursoId) {
        ServiceInstance serviceInstance = loadBalancerClient.choose("ms-curso-service");

        if (serviceInstance != null) {
            String baseUrl = serviceInstance.getUri().toString();
            String url = baseUrl + "/cursos/" + cursoId + "/decrementar-inscritos";

            try {
                restTemplate.postForObject(url, null, Void.class);
            } catch (Exception e) {
                throw new RuntimeException("Error al llamar a decrementar inscritos en el curso con ID " + cursoId + ": " + e.getMessage());
            }
        } else {
            throw new RuntimeException("No se encontraron instancias del servicio ms-curso-service en Eureka.");
        }
    }
}
