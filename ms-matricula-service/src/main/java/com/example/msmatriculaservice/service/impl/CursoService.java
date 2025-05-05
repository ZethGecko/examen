package com.example.msmatriculaservice.service.impl;

import com.example.msmatriculaservice.dto.Curso;
import com.example.msmatriculaservice.dto.Estudiante;
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

    public Curso obtenerCursoPorId(Integer cursoId) {
        ServiceInstance serviceInstance = loadBalancerClient.choose("ms-curso-service");

        if (serviceInstance != null) {
            String baseUrl = serviceInstance.getUri().toString();
            String url = baseUrl + "/cursos/" + cursoId;

            try {
                return restTemplate.getForObject(url, Curso.class);
            } catch (Exception e) {
                throw new RuntimeException("Error al obtener curs con ID " + cursoId + ": " + e.getMessage());
            }
        }

        throw new RuntimeException("No se encontraron instancias del servicio ms-curso-service en Eureka.");
    }

    public void reducirCapacidadCurso(Integer cursoId) {
        ServiceInstance serviceInstance = loadBalancerClient.choose("ms-curso-service");

        if (serviceInstance != null) {
            String baseUrl = serviceInstance.getUri().toString();
            String url = baseUrl + "/cursos/" + cursoId + "/reducir-capacidad";

            try {
                restTemplate.put(url, null);
                System.out.println("Capacidad reducida en el curso con ID: " + cursoId);
            } catch (Exception e) {
                throw new RuntimeException("Error al reducir capacidad del curso con ID " + cursoId + ": " + e.getMessage());
            }
        } else {
            throw new RuntimeException("No se encontraron instancias del servicio ms-curso-service en Eureka.");
        }
    }

    public void aumentarCapacidadCurso(Integer cursoId) {
        ServiceInstance serviceInstance = loadBalancerClient.choose("ms-curso-service");

        if (serviceInstance != null) {
            String baseUrl = serviceInstance.getUri().toString();
            String url = baseUrl + "/cursos/" + cursoId + "/aumentar-capacidad";

            try {
                restTemplate.put(url, null);
                System.out.println("Capacidad restaurada en el curso con ID: " + cursoId);
            } catch (Exception e) {
                throw new RuntimeException("Error al aumentar capacidad del curso con ID " + cursoId + ": " + e.getMessage());
            }
        } else {
            throw new RuntimeException("No se encontraron instancias del servicio ms-curso-service en Eureka.");
        }
    }
}
