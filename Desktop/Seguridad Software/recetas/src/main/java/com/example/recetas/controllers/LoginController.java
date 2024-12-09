package com.example.recetas.controllers;

import com.example.recetas.model.Users;
import com.example.recetas.JWTAuthenticationConfig;
import com.example.recetas.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.recetas.exception.ResourceNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;



@RestController
public class LoginController {

    @Autowired
    JWTAuthenticationConfig jwtAuthtenticationConfig;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<String> login(
        @RequestParam("user") String username,
        @RequestParam("encryptedPass") String password) {

        Users userDetails = userService.obtenerUsuarioByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User " + username + " not found"));

        if (!userDetails.getPassword().equals(password)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid login");
        }

        String token = jwtAuthtenticationConfig.getJWTToken(username);
        return ResponseEntity.ok(token);
    }

    @GetMapping("users")
     public ResponseEntity<List<Users>> getUsers() {
        List<Users> users = userService.obtenerListadoUsuarios();
        return ResponseEntity.ok(users);
    }
    

}
