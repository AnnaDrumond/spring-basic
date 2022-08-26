package com.io.github.annadrumond.springbasic.services;

//https://acervolima.com/introducao-ao-spring-boot/

import com.io.github.annadrumond.springbasic.entities.Order;
import com.io.github.annadrumond.springbasic.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    //Buscar lista de todos os Orders na bd H2
    public List<Order> findAll(){
        return orderRepository.findAll();
    }

    public Order findById(Long id){
        //https://medium.com/@danielchristofolli/optional-java-f5fad98fa247
        //http://mauda.com.br/?p=1383
        //https://www.baeldung.com/java-optional
        Optional<Order> OrderOptional = orderRepository.findById(id);
        // O método get() abaixo é da classe Optional e vai retonar o objeto Order armazenado em OrderOptional
        // Se busco algo que não existe na minha BD H2, o Optional fica com valor EMPTY
        if (OrderOptional.isEmpty()){
            return null;
        }
        return OrderOptional.get();
    }
}
