package com.example.recetas.model;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class UsersTest {
    @Test
    void testGettersAndSetters() {
        // Arrange
        Users user = new Users();
        user.setUsername("Jose");
        user.setPassword("Pass");

        // Act & Assert
        assertThat(user.getUsername()).isEqualTo("Jose");
        assertThat(user.getPassword()).isEqualTo("Pass");
        
    }
}
