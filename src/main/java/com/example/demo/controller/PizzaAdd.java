package com.example.demo.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Configuration
public class PizzaAdd{

    public record Request(String id, String nombre){
    }

    public record Response(String id, String nombre) {
    }

    @RestController
    @RequestMapping("/pizzas")
    public class Controller{
        
        @PostMapping
        public ResponseEntity<?> action(@RequestBody Request request){
            var response = new Response(request.id, request.nombre);
            return ResponseEntity.status(201).body(response);
        }
    }
}