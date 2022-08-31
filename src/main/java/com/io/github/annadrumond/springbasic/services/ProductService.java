package com.io.github.annadrumond.springbasic.services;

//https://acervolima.com/introducao-ao-spring-boot/

import com.io.github.annadrumond.springbasic.entities.Product;

import com.io.github.annadrumond.springbasic.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    //Buscar lista de todos os Products na bd H2
    public List<Product> findAll(){
        return productRepository.findAll();
    }

    public Product findById(Long id){
        //https://medium.com/@danielchristofolli/optional-java-f5fad98fa247
        //http://mauda.com.br/?p=1383
        //https://www.baeldung.com/java-optional
        Optional<Product> productOptional = productRepository.findById(id);
        // O método get() abaixo é da classe Optional e vai retonar o objeto Product armazenado em ProductOptional
        // Se busco algo que não existe na minha BD H2, o Optional fica com valor EMPTY
        if (productOptional.isEmpty()){
            return null;
        }
        return productOptional.get();
    }
}
