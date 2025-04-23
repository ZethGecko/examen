package com.example.msmatriculaservice.service;

import com.example.msmatriculaservice.dto.MatriculaDetalle;
import com.example.msmatriculaservice.entity.Matricula;

import java.util.List;
import java.util.Optional;

public interface MatriculaService {
    public List<Matricula> listar();
    public Matricula guardar(Matricula matricula);
    public Matricula actualizar(Matricula matricula);
    public Optional<Matricula> listarPorId(Integer id);
    public void eliminarPorId(Integer id);

    MatriculaDetalle obtenerDetalleMatriculaPorId(Integer matriculaId);

    List<MatriculaDetalle> obtenerReporteMatriculas();
}
