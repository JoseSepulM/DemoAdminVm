package com.example.recetas.model;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class RecetaTest {

    @Test
    void testGettersAndSetters() {
        // Arrange
        Receta receta = new Receta();
        receta.setNombre("Tarta de Manzana");
        receta.setAnioCreacion(2020);
        receta.setVigente("Sí");

        // Act & Assert
        assertThat(receta.getNombre()).isEqualTo("Tarta de Manzana");
        assertThat(receta.getAnioCreacion()).isEqualTo(2020);
        assertThat(receta.getVigente()).isEqualTo("Sí");
    }
}
