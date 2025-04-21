package com.example.msestudianteservice.controller;

import com.example.msestudianteservice.entity.Estudiante;
import com.example.msestudianteservice.service.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estudiantes")
public class EstudianteController {
    @Autowired
    private EstudianteService estudianteService;

    @GetMapping()
    public ResponseEntity<List<Estudiante>> list() {
        return ResponseEntity.ok().body(estudianteService.listar());
    }
    @PostMapping()
    public ResponseEntity<?> save(@RequestBody Estudiante estudiante) {
        try {
            // Llama al servicio para guardar el estudiante
            Estudiante nuevoEstudiante = estudianteService.guardar(estudiante);
            return ResponseEntity.ok(nuevoEstudiante);
        } catch (RuntimeException e) {
            // Maneja cualquier excepción y devuelve un mensaje claro
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping()
    public ResponseEntity<Estudiante> update(@RequestBody Estudiante estudiante){
        return ResponseEntity.ok(estudianteService.actualizar(estudiante));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Estudiante> listById(@PathVariable(required = true) Integer id){
        return ResponseEntity.ok().body(estudianteService.listarPorId(id).get());
    }
    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable(required = true) Integer id){
        estudianteService.eliminarPorId(id);
        return "Eliminacion Correcta";
    }
}
