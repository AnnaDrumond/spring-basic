package com.io.github.annadrumond.springbasic.controllers;

import com.io.github.annadrumond.springbasic.entities.User;
import com.io.github.annadrumond.springbasic.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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


    @PostMapping(value = "/insert")
    //@RequestBody - similar ao consumes.MediaType Application_Json, ou seja vai permitir que o objeto Json recebido
    //Seja serializado para um objeto Java
    //No POSTMAN é necessário colocado em Headers key: Content-Type value: application/json
    public ResponseEntity<User> insertUser( @RequestBody User user){
        user = userService.insertUser(user);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(user.getId()).toUri();
        //Para enviarmos a resposta correta (201 - objeto criado), é necessário construir a resposta na forma acima
        // Esta resposta http 201, é usada nos casos em que está sendo criado um novo objeto
        return ResponseEntity.created(uri).body(user);
    }


    @DeleteMapping(value = "/delete/{idUser}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long idUser){
        userService.deleteUser(idUser);
        //construir resposta de uma requisição que não tem uma resposta
        return ResponseEntity.noContent().build();
    }

    // Para atualizar, usar o PUT
    //No POSTMAN é necessário colocado em Headers key: Content-Type value: application/json
    //@RequestBody - similar ao consumes.MediaType Application_Json, ou seja vai permitir que o objeto Json recebido
    //Seja serializado para um objeto Java
    @PutMapping(value = "/update/{idUser}")
    public ResponseEntity<User> updateUser(@RequestBody User userNewData, @PathVariable Long idUser){
        userNewData = userService.updateUser(idUser, userNewData);
        return ResponseEntity.ok().body(userNewData);
    }


}
