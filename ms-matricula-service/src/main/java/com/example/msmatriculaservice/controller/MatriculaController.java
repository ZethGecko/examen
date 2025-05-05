package com.example.msmatriculaservice.controller;

import com.example.msmatriculaservice.dto.Curso;
import com.example.msmatriculaservice.dto.Estudiante;
import com.example.msmatriculaservice.entity.Matricula;
import com.example.msmatriculaservice.repository.MatriculaRepository;
import com.example.msmatriculaservice.service.EstudianteService;
import com.example.msmatriculaservice.service.MatriculaService;
import com.example.msmatriculaservice.service.impl.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/matriculas")
public class MatriculaController {
    @Autowired
    private MatriculaService matriculaService;
    @Autowired
    private EstudianteService estudianteService;
    @Autowired
    private CursoService cursoService;
    @Autowired
    private MatriculaRepository matriculaRepository;

    @GetMapping()
    public ResponseEntity<List<Matricula>> list() {
        List<Matricula> matriculas = matriculaService.listar();

        for (Matricula matricula : matriculas) {
            // ✅ Obtener el curso desde el microservicio de cursos
            Curso curso = cursoService.obtenerCursoPorId(matricula.getCursoId());

            // ✅ Obtener el estudiante desde el microservicio de estudiantes
            Estudiante estudiante = estudianteService.obtenerEstudiantePorId(matricula.getAlumnoId());

            // Si el servicio de cursos está apagado, usamos fallback (Curso Desconocido)
            if (curso == null) {
                curso = new Curso();
                curso.setId(matricula.getCursoId());
                curso.setNombre("Curso Desconocido");
                curso.setCapacidad(0);
                curso.setInscritos(0);
            }

            // Si el servicio de estudiantes está apagado, usamos fallback (Estudiante Desconocido)
            if (estudiante == null) {
                estudiante = new Estudiante();
                estudiante.setId(matricula.getAlumnoId());
                estudiante.setNombre("Desconocido");
                estudiante.setEstado("Inactivo");
            }

            // ✅ Asignar los datos antes de devolver la lista
            matricula.setCursoNombre(curso.getNombre());
            matricula.setAlumnoNombre(estudiante.getNombre());
        }

        return ResponseEntity.ok().body(matriculas);
    }


    @PostMapping()
    public ResponseEntity<Matricula> save(@RequestBody Matricula matricula) {
        return ResponseEntity.ok(matriculaService.guardar(matricula));
    }
    @PutMapping()
    public ResponseEntity<Matricula> update(@RequestBody Matricula matricula){
        return ResponseEntity.ok(matriculaService.actualizar(matricula));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Matricula> obtenerMatriculaPorId(@PathVariable Integer id) {
        Matricula matricula = matriculaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Matrícula no encontrada con ID: " + id));

        // ✅ Obtener el curso desde el microservicio de cursos
        Curso curso = cursoService.obtenerCursoPorId(matricula.getCursoId());

        // ✅ Obtener el estudiante desde el microservicio de estudiantes
        Estudiante estudiante = estudianteService.obtenerEstudiantePorId(matricula.getAlumnoId());

        // Si el servicio de cursos está apagado, usamos fallback (Curso Desconocido)
        if (curso == null) {
            curso = new Curso();
            curso.setId(matricula.getCursoId());
            curso.setNombre("Curso Desconocido");
            curso.setCapacidad(0);
            curso.setInscritos(0);
        }

        // Si el servicio de estudiantes está apagado, usamos fallback (Estudiante Desconocido)
        if (estudiante == null) {
            estudiante = new Estudiante();
            estudiante.setId(matricula.getAlumnoId());
            estudiante.setNombre("Desconocido");
            estudiante.setEstado("Inactivo");
        }

        // ✅ Asignar los datos dinámicamente antes de devolver la matrícula
        matricula.setCursoNombre(curso.getNombre());
        matricula.setAlumnoNombre(estudiante.getNombre());

        return ResponseEntity.ok(matricula);
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable(required = true) Integer id){
        matriculaService.eliminarPorId(id);
        return "Eliminacion Correcta";
    }
}