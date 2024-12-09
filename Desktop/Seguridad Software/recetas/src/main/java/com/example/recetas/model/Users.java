package com.example.recetas.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;



@Entity
@Table( name = "Users" )
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    // GET
    public Long getId(){
        return id;
    }

    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }

    // SET

    public void setUsername( String username ){
        this.username = username;
    }

    public void setPassword ( String pass ){
        this.password = pass;
    }



    
}