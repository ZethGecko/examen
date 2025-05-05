package com.example.msmatriculaservice.controller;

import com.example.msmatriculaservice.entity.Matricula;
import com.example.msmatriculaservice.service.MatriculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/matriculas")
public class MatriculaController {
    @Autowired
    private MatriculaService matriculaService;

    @GetMapping()
    public ResponseEntity<List<Matricula>> list() {
        return ResponseEntity.ok().body(matriculaService.listar());
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
    public ResponseEntity<Matricula> listById(@PathVariable(required = true) Integer id){
        return ResponseEntity.ok().body(matriculaService.listarPorId(id).get());
    }
    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable(required = true) Integer id){
        matriculaService.eliminarPorId(id);
        return "Eliminacion Correcta";
    }
}