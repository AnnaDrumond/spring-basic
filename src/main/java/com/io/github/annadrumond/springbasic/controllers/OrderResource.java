package com.io.github.annadrumond.springbasic.controllers;

import com.io.github.annadrumond.springbasic.entities.Order;
import com.io.github.annadrumond.springbasic.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping(value = "/orders")//definição  da url deste controller
//https://www.baeldung.com/spring-controller-vs-restcontroller
//@RestController que combina o comportamento do @Controller e do @ResponseBody.
//è a meu controlador - REST
public class OrderResource {
    // Os resources são os serviços REST/ Controllers

    @Autowired
    private OrderService orderService;
    /**
     * O ResponseEntity é Generics <T> e no lugar do T devo colocar o tipo de resposta que espero
     * O getMapping indica que é um GET
     */
    @GetMapping // significa que é uma requisição do tipo GET
    // <List<User>> é o tipo de retorno
    public ResponseEntity<List<Order>> findAll(){
        List <Order> users = orderService.findAll();
        return ResponseEntity.ok().body(users);
    }

    @GetMapping(value = "/find/by/{orderId}")
    public ResponseEntity<Order> findById(@PathVariable Long orderId){

        Order orderFound = orderService.findById(orderId);

        if (orderFound != null){
            return ResponseEntity.ok().body(orderFound);
        }
        return ResponseEntity.status(404).build();
    }





}
