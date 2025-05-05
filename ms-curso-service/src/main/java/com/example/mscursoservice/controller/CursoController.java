package com.example.mscursoservice.controller;

import com.example.mscursoservice.entity.Cursos;
import com.example.mscursoservice.repository.CursoRepository;
import com.example.mscursoservice.service.CursoService;
import com.example.mscursoservice.service.impl.CursoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cursos")
public class CursoController {
    @Autowired
    private CursoService cursoService;
    @Autowired
    private CursoRepository cursoRepository;

    @GetMapping()
    public ResponseEntity<List<Cursos>> list() {
        return ResponseEntity.ok().body(cursoService.listar());
    }

    @PostMapping()
    public ResponseEntity<Cursos> save(@RequestBody Cursos cursos) {
        return ResponseEntity.ok(cursoService.guardar(cursos));
    }

    @PutMapping()
    public ResponseEntity<Cursos> update(@RequestBody Cursos cursos) {
        return ResponseEntity.ok(cursoService.actualizar(cursos));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cursos> listById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(cursoService.listarPorId(id).orElseThrow(() -> new RuntimeException("Curso no encontrado con ID " + id)));
    }

    @GetMapping("/{id}/nombre")
    public ResponseEntity<String> obtenerNombreCurso(@PathVariable Integer id) {
        String nombreCurso = cursoService.obtenerNombrePorId(id);
        return ResponseEntity.ok(nombreCurso);
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable Integer id) {
        cursoService.eliminarPorId(id);
        return "Eliminación Correcta";
    }

    // Nuevo endpoint para inscribir estudiantes
    @PostMapping("/{id}/inscribir")
    public ResponseEntity<?> inscribirEstudiante(@PathVariable Integer id) {
        try {
            Cursos curso = ((CursoServiceImpl) cursoService).inscribirEstudiante(id);
            return ResponseEntity.ok(curso);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}/reducir-capacidad")
    public ResponseEntity<String> reducirCapacidad(@PathVariable Integer id) {
        Cursos curso = cursoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Curso no encontrado con ID: " + id));

        if (curso.getCapacidad() > 0) {
            curso.setCapacidad(curso.getCapacidad() - 1);
            cursoRepository.save(curso);
            return ResponseEntity.ok("Capacidad reducida a " + curso.getCapacidad());
        } else {
            return ResponseEntity.badRequest().body("El curso ya ha alcanzado su capacidad máxima.");
        }
    }

    @PutMapping("/{id}/aumentar-capacidad")
    public ResponseEntity<String> aumentarCapacidad(@PathVariable Integer id) {
        Cursos curso = cursoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Curso no encontrado con ID: " + id));

        curso.setCapacidad(curso.getCapacidad() + 1);
        cursoRepository.save(curso);
        return ResponseEntity.ok("Capacidad aumentada a " + curso.getCapacidad());
    }
}
