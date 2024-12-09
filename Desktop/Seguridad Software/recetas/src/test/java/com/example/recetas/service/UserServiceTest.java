package com.example.recetas.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.recetas.model.Users;
import com.example.recetas.repository.UserRepository;

@SpringBootTest
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void obtenerListadoUsuariosTest() {
        // Creacion datos de prueba
        Users user1 = new Users();
        user1.setUsername("user1");
        Users user2 = new Users();
        user2.setUsername("user2");

        List<Users> usuarios = List.of(user1, user2);

        when(userRepository.findAll()).thenReturn(usuarios);

        List<Users> result = userService.obtenerListadoUsuarios();
        assertEquals(2, result.size());  
        assertEquals("user1", result.get(0).getUsername());
        assertEquals("user2", result.get(1).getUsername());
    }

    @Test
    public void obtenerUsuarioTest() {
        Long userId = 1L;
        Users user = new Users();
        user.setUsername("user1");

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        Optional<Users> result = userService.obtenerUsuario(userId);
        assertTrue(result.isPresent());
        assertEquals("user1", result.get().getUsername());
    }

    @Test
    public void obtenerUsuarioByUsernameTest() {
        String username = "testUser";
        Users user = new Users();
        user.setUsername(username);

        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));

        Optional<Users> result = userService.obtenerUsuarioByUsername(username);
        assertTrue(result.isPresent());
        assertEquals(username, result.get().getUsername());
    }

    @Test
    public void guardarUsuarioTest() {
        Users usuario = new Users();
        usuario.setUsername("newUser");
        when(userRepository.save(usuario)).thenReturn(usuario);
        Users result = userService.guardarUsuario(usuario);
        assertEquals("newUser", result.getUsername());  
    }

    @Test
    public void deleteUserTest() {
        Long userId = 1L;
        userService.deleteUser(userId);
        verify(userRepository, times(1)).deleteById(userId);
    }
}
