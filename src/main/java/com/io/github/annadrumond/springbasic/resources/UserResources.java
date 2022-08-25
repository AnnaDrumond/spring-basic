package com.io.github.annadrumond.springbasic.resources;

import com.io.github.annadrumond.springbasic.entities.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users")
//https://www.baeldung.com/spring-controller-vs-restcontroller
//@RestController que combina o comportamento do @Controller e do @ResponseBody.
//
public class UserResources {
    // Os resources são os serviçols REST/ Controllers

    /**
     * O ResponseEntity é Generics <T> e no lugar do T devo colocar o tipo de resposta que espero
     * O getMapping indica que é um GET
     */
    @GetMapping
    public ResponseEntity<User> findAll(){

        User user = new User(1L,"Anna","anna@pt","999","abcd");

        return ResponseEntity.ok().body(user);
    }


}
