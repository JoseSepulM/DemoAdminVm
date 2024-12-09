package com.example.recetas.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@Controller
public class ViewController {

    @GetMapping("/hello")
    public String hello(Model model) {
        model.addAttribute("name", "Mundo");
        return "hello";
    }

    // @GetMapping("/login")
    // public String login(Model model) {
    //     return "login";
    // }

    @GetMapping("/recetaView")
    public String receta(Model model, @RequestHeader("Authorization") String authorization) {
        if (authorization != null && authorization.startsWith("Bearer ")) {
            String token =  authorization.substring(7);
            model.addAttribute("token", token);
            return "receta";
        }
    
        return "redirect:/login";
    }
}
