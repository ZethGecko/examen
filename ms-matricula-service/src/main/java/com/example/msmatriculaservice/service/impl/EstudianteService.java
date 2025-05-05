package com.example.msmatriculaservice.service;

import com.example.msmatriculaservice.dto.Estudiante;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
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

    @CircuitBreaker(name = "estudianteService", fallbackMethod = "fallbackEstudiante")
    public Estudiante obtenerEstudiantePorId(Integer alumnoId) {
        ServiceInstance serviceInstance = loadBalancerClient.choose("ms-estudiante-service");

        if (serviceInstance != null) {
            String baseUrl = serviceInstance.getUri().toString();
            String url = baseUrl + "/estudiantes/" + alumnoId; // ✅ Cambio la ruta

            try {
                return restTemplate.getForObject(url, Estudiante.class);
            } catch (Exception e) {
                throw new RuntimeException("Error al obtener estudiante con ID " + alumnoId + ": " + e.getMessage());
            }
        }
        throw new RuntimeException("No se encontraron instancias del servicio ms-estudiante-service en Eureka.");
    }

    //Metodo de respaldo cuando el servicio falla
    public Estudiante fallbackEstudiante(Integer alumnoId, Throwable throwable) {
        System.out.println("Circuit Breaker activado. Error: " + throwable.getMessage());

        Estudiante estudiante = new Estudiante(); // ✅ Instancia vacía
        estudiante.setId(alumnoId);
        estudiante.setNombre("Desconocido");
        estudiante.setEstado("Inactivo");

        return estudiante;
    }



//    public Estudiante obtenerEstudiantePorId(Integer alumnoId) {
//        ServiceInstance serviceInstance = loadBalancerClient.choose("ms-estudiante-service");
//
//        if (serviceInstance != null) {
//            String baseUrl = serviceInstance.getUri().toString();
//            String url = baseUrl + "/estudiantes/" + alumnoId; // ✅ Cambio la ruta a "/estudiantes/"
//
//            try {
//                Estudiante estudiante = restTemplate.getForObject(url, Estudiante.class);
//                if (estudiante == null) {
//                    throw new RuntimeException("Estudiante con ID " + alumnoId + " no encontrado.");
//                }
//                return estudiante;
//            } catch (Exception e) {
//                throw new RuntimeException("Error al obtener estudiante con ID " + alumnoId + ": " + e.getMessage());
//            }
//        }
//
//        throw new RuntimeException("No se encontraron instancias del servicio ms-estudiante-service en Eureka.");
//    }
}
