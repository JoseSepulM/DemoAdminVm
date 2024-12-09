package com.example.recetas.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.recetas.model.Receta;
import com.example.recetas.repository.RecetaRepository;

@SpringBootTest
public class RecetaServiceTest {

    @Mock
    private RecetaRepository recetaRepository;

    @InjectMocks
    private RecetaService recetaService;

    @Test
    public void obtenerListadoRecetasTest() {
        // Crear datos de prueba
        Receta receta1 = new Receta();
        receta1.setNombre("Receta 1");
        Receta receta2 = new Receta();
        receta2.setNombre("Receta 2");

        List<Receta> recetas = List.of(receta1, receta2);

        // Simula la respuesta del método findAll del repositorio
        when(recetaRepository.findAll()).thenReturn(recetas);

        // Llama al servicio y verifica el resultado
        List<Receta> result = recetaService.obtenerListadoRecetas();
        assertEquals(2, result.size());  // Verifica que la lista contiene 2 recetas
        assertEquals("Receta 1", result.get(0).getNombre());  // Verifica el nombre de la primera receta
        assertEquals("Receta 2", result.get(1).getNombre());  // Verifica el nombre de la segunda receta
    }

    @Test
    public void obtenerRecetaTest() {
        Long recetaId = 1L;
        Receta receta = new Receta();
        receta.setNombre("Receta 1");

        // Simula la respuesta del método findById del repositorio
        when(recetaRepository.findById(recetaId)).thenReturn(Optional.of(receta));

        // Llama al servicio y verifica el resultado
        Optional<Receta> result = recetaService.obtenerReceta(recetaId);
        assertTrue(result.isPresent());  // Verifica que la receta está presente
        assertEquals("Receta 1", result.get().getNombre());  // Verifica el nombre de la receta
    }

    @Test
    public void guardarRecetaTest() {
        Receta receta = new Receta();
        receta.setNombre("Receta Guardada");

        // Simula la respuesta del método save del repositorio
        when(recetaRepository.save(receta)).thenReturn(receta);

        // Llama al servicio y verifica el resultado
        Receta result = recetaService.guardarReceta(receta);
        assertEquals("Receta Guardada", result.getNombre());  // Verifica que el nombre de la receta es el esperado
    }

    @Test
    public void eliminarRecetaTest() {
        Long recetaId = 1L;

        // Llama al servicio para eliminar la receta
        recetaService.eliminarReceta(recetaId);

        // Verifica que el método deleteById fue llamado en el repositorio
        verify(recetaRepository, times(1)).deleteById(recetaId);
    }
}
