package com.io.github.annadrumond.springbasic.controllers;

import com.io.github.annadrumond.springbasic.entities.User;
import com.io.github.annadrumond.springbasic.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/users")//definição  da url deste controller
//https://www.baeldung.com/spring-controller-vs-restcontroller
//@RestController que combina o comportamento do @Controller e do @ResponseBody.
//è a meu controlador - REST
public class UserResource {
    // Os resources são os serviços REST/ Controllers

    @Autowired
    private UserService userService;
    /**
     * O ResponseEntity é Generics <T> e no lugar do T devo colocar o tipo de resposta que espero
     * O getMapping indica que é um GET
     */
    @GetMapping // significa que é uma requisição do tipo GET
    // <List<User>> é o tipo de retorno
    public ResponseEntity<List<User>> findAll(){
        List <User> users = userService.findAll();
        return ResponseEntity.ok().body(users);
    }

    @GetMapping(value = "/find/by/{userId}")
    public ResponseEntity<User> findById(@PathVariable Long userId){

        User userFound = userService.findById(userId);

        if (userFound != null){
            return ResponseEntity.ok().body(userFound);
        }
        return ResponseEntity.status(404).build();
    }





}
