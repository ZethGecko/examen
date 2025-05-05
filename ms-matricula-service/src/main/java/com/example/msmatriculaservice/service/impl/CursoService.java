package com.example.msmatriculaservice.service.impl;

import com.example.msmatriculaservice.dto.Curso;
import com.example.msmatriculaservice.dto.Estudiante;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
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

    @CircuitBreaker(name = "cursoService", fallbackMethod = "fallbackCurso")
    public Curso obtenerCursoPorId(Integer cursoId) {
        ServiceInstance serviceInstance = loadBalancerClient.choose("ms-curso-service");

        if (serviceInstance != null) {
            String baseUrl = serviceInstance.getUri().toString();
            String url = baseUrl + "/cursos/" + cursoId; // ✅ Ruta corregida

            try {
                Curso curso = restTemplate.getForObject(url, Curso.class);
                if (curso == null) {
                    throw new RuntimeException("Curso con ID " + cursoId + " no encontrado.");
                }
                return curso;
            } catch (Exception e) {
                throw new RuntimeException("Error al obtener curso con ID " + cursoId + ": " + e.getMessage());
            }
        }

        throw new RuntimeException("No se encontraron instancias del servicio ms-curso-service en Eureka.");
    }

    //Metodo de respaldo cuando el servicio falla
    public Curso fallbackCurso(Integer cursoId, Throwable throwable) {
        System.out.println("Circuit Breaker activado. Error: " + throwable.getMessage());

        Curso curso = new Curso(); // ✅ Instancia vacía
        curso.setId(cursoId);
        curso.setNombre("Curso Desconocido");
        curso.setCapacidad(0);
        curso.setInscritos(0);

        return curso;
    }


//    public Curso obtenerCursoPorId(Integer cursoId) {
//        ServiceInstance serviceInstance = loadBalancerClient.choose("ms-curso-service");
//
//        if (serviceInstance != null) {
//            String baseUrl = serviceInstance.getUri().toString();
//            String url = baseUrl + "/cursos/" + cursoId;
//
//            try {
//                return restTemplate.getForObject(url, Curso.class);
//            } catch (Exception e) {
//                throw new RuntimeException("Error al obtener curs con ID " + cursoId + ": " + e.getMessage());
//            }
//        }
//
//        throw new RuntimeException("No se encontraron instancias del servicio ms-curso-service en Eureka.");
//    }

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
