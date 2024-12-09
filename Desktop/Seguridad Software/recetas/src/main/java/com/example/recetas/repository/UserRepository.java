package com.example.recetas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.recetas.model.Users;
import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long>{

    Optional<Users> findByUsername(String username);
    
}