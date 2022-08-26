package com.io.github.annadrumond.springbasic.services;

//https://acervolima.com/introducao-ao-spring-boot/

import com.io.github.annadrumond.springbasic.entities.User;
import com.io.github.annadrumond.springbasic.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    //Buscar lista de todos os users na bd H2
    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User findById(Long id){
        //https://medium.com/@danielchristofolli/optional-java-f5fad98fa247
        //http://mauda.com.br/?p=1383
        //https://www.baeldung.com/java-optional
        Optional<User> userOptional = userRepository.findById(id);
        // O método get() abaixo é da classe Optional e vai retonar o objeto User armazenado em userOptional
        // Se busco algo que não existe na minha BD H2, o Optional fica com valor EMPTY
        if (userOptional.isEmpty()){
            return null;
        }
        return userOptional.get();
    }
}
