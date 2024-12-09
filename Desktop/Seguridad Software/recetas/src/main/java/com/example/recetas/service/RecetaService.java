package com.example.recetas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.recetas.model.Receta;
import com.example.recetas.repository.RecetaRepository;

@Service
public class RecetaService {

    @Autowired
    private RecetaRepository recetaRepository;

    public List<Receta> obtenerListadoRecetas() {
        return recetaRepository.findAll();
    }

    public Optional<Receta> obtenerReceta(Long id) {
        return recetaRepository.findById(id);
    }

    public Receta guardarReceta(Receta receta) {
        return recetaRepository.save(receta);
    }

    public void eliminarReceta(Long id) {
        recetaRepository.deleteById(id);
    }
}
