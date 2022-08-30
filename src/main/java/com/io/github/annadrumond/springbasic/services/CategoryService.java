package com.io.github.annadrumond.springbasic.services;

//https://acervolima.com/introducao-ao-spring-boot/

import com.io.github.annadrumond.springbasic.entities.Category;
import com.io.github.annadrumond.springbasic.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    //Buscar lista de todos os Categorys na bd H2
    public List<Category> findAll(){

        return categoryRepository.findAll();
    }

    public Category findById(Long id){
        //https://medium.com/@danielchristofolli/optional-java-f5fad98fa247
        //http://mauda.com.br/?p=1383
        //https://www.baeldung.com/java-optional
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        // O método get() abaixo é da classe Optional e vai retonar o objeto Category armazenado em CategoryOptional
        // Se busco algo que não existe na minha BD H2, o Optional fica com valor EMPTY
        if (categoryOptional.isEmpty()){
            return null;
        }
        return categoryOptional.get();
    }
}
