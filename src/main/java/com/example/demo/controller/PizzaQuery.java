package com.example.demo.controller;

import java.util.ArrayList;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Configuration
public class PizzaQuery {

    public record Response(String id, String name) {
    }

    @RestController
    @RequestMapping("/pizzas")
    public class Controller {

        @GetMapping
        public ResponseEntity<?> action(@RequestParam(required = false) String name,
                @RequestParam(defaultValue = "0") int page,
                @RequestParam(defaultValue = "10") int size) {
            var list = listaDePizzas();

            return ResponseEntity.status(200).body(list);
        }

        @GetMapping(path = "/{id}")
        public ResponseEntity<?> action(@PathVariable String id){
            var list = listaDePizzas();

            var item = list.stream().filter(p -> p.id.equals(id)).findAny();
            if(!item.isEmpty()){
                return ResponseEntity.status(200).body(item.get());
            }

            return ResponseEntity.status(404).body(null);
        }

        private ArrayList<Response> listaDePizzas(){
            var list = new ArrayList<Response>();
            list.add(new Response("1", "Margarita"));
            list.add(new Response("2", "Barbacoa"));
            list.add(new Response("3", "Carbonara"));
            return list;
        }
    }
}
