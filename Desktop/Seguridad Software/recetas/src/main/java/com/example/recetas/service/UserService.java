package com.example.recetas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.recetas.model.Users;
import com.example.recetas.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<Users> obtenerListadoUsuarios(){
        return userRepository.findAll();
    }

    public Optional<Users> obtenerUsuario( Long id ){
        return userRepository.findById(id);
    }

    public Users guardarUsuario( Users usuario ){
        return userRepository.save(usuario);
    }

    public void deleteUser( Long id ){
        userRepository.deleteById(id);
    }

    public Optional<Users> obtenerUsuarioByUsername( String username){
        return userRepository.findByUsername(username);
    }
    
}