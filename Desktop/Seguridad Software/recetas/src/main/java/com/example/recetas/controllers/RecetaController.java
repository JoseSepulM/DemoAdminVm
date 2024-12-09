package com.example.recetas.controllers;

import java.util.List;
import com.example.recetas.model.Receta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.example.recetas.service.RecetaService;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
public class RecetaController {

    @Autowired
    private RecetaService recetaService;

    @GetMapping("/receta")
    public List<Receta> getRecetas() {
        List<Receta> recetas = recetaService.obtenerListadoRecetas();
        return recetas;
    }
    
    
}
