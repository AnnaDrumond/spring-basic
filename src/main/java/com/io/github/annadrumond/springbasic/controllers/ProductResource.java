package com.io.github.annadrumond.springbasic.controllers;

import com.io.github.annadrumond.springbasic.entities.Product;
import com.io.github.annadrumond.springbasic.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/products")//definição  da url deste controller
//https://www.baeldung.com/spring-controller-vs-restcontroller
//@RestController que combina o comportamento do @Controller e do @ResponseBody.
//è a meu controlador - REST
public class ProductResource {
    // Os resources são os serviços REST/ Controllers

    @Autowired
    private ProductService productService;
    /**
     * O ResponseEntity é Generics <T> e no lugar do T devo colocar o tipo de resposta que espero
     * O getMapping indica que é um GET
     */
    @GetMapping // significa que é uma requisição do tipo GET
    // <List<Product>> é o tipo de retorno
    public ResponseEntity<List<Product>> findAll(){
        List <Product> products = productService.findAll();
        return ResponseEntity.ok().body(products);
    }

    @GetMapping(value = "/find/by/{productId}")
    public ResponseEntity<Product> findById(@PathVariable Long productId){

        Product productFound = productService.findById(productId);

        if (productFound != null){
            return ResponseEntity.ok().body(productFound);
        }
        return ResponseEntity.status(404).build();
    }





}
