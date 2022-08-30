package com.io.github.annadrumond.springbasic.controllers;

import com.io.github.annadrumond.springbasic.entities.Category;
import com.io.github.annadrumond.springbasic.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/categories")//definição  da url deste controller
//https://www.baeldung.com/spring-controller-vs-restcontroller
//@RestController que combina o comportamento do @Controller e do @ResponseBody.
//è a meu controlador - REST
public class CategoryResource {
    // Os resources são os serviços REST/ Controllers

    @Autowired
    private CategoryService categoryService;
    /**
     * O ResponseEntity é Generics <T> e no lugar do T devo colocar o tipo de resposta que espero
     * O getMapping indica que é um GET
     */
    @GetMapping // significa que é uma requisição do tipo GET
    // <List<Category>> é o tipo de retorno
    public ResponseEntity<List<Category>> findAll(){
        List <Category> Categorys = categoryService.findAll();
        return ResponseEntity.ok().body(Categorys);
    }

    //Buscar por id
    @GetMapping(value = "/find/by/{CategoryId}")
    public ResponseEntity<Category> findById(@PathVariable Long CategoryId){

        Category categoryFound = categoryService.findById(CategoryId);

        if (categoryFound != null){
            return ResponseEntity.ok().body(categoryFound);
        }
        return ResponseEntity.status(404).build();
    }





}
