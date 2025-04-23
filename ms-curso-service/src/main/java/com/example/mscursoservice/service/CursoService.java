package com.example.mscursoservice.service;

import com.example.mscursoservice.entity.Cursos;

import java.util.List;
import java.util.Optional;

public interface CursoService {
    public List<Cursos> listar();
    public Cursos guardar(Cursos cursos);
    public Cursos actualizar(Cursos cursos);
    public Optional<Cursos> listarPorId(Integer id);
    public void eliminarPorId(Integer id);


    String obtenerNombrePorId(Integer id);
}
