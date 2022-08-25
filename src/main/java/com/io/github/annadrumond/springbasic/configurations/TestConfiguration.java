package com.io.github.annadrumond.springbasic.configurations;

import com.io.github.annadrumond.springbasic.entities.User;
import com.io.github.annadrumond.springbasic.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

/**
 * Esta classe será usada para fazer o database seeding e popular a base de dados H2 (bd de teste) com alguns objetos
 */
@Configuration
//Definir que esta classe é especifica para p erfil de teste
@Profile("test")// aqui entra a mesma palavra que coloquei no aplication.properties linha 1
public class TestConfiguration implements CommandLineRunner {

    //Injetar a dependencia de UserRepository, pois é ela quem envia dados para a minha Bd h2
    //Marks a constructor, field, setter method, or config method as to be autowired by Spring's dependency injection facilities.
    // This is an alternative to the JSR-330 javax.inject.Inject annotation, adding required-vs-optional semantics.
    @Autowired
    private UserRepository userRepository;

    //Agora preciso garantir que a bd seja populada assim que o meu programa começar a correr
    //Nestre exemplo vamos fazer a classe implementar a interface ComandLineRunner
    //Isso obriga a implmentação do método run() - tudo que estiver dentro deste método será executado quando a app for iniciada
    @Override
    public void run(String... args) throws Exception {
        //id começa a null, pois será gerado pela bd
        User user1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
        User user2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");
        //O saveAll é um método Java da interface JpaRepositor
        userRepository.saveAll(Arrays.asList(user1,user2));
    }
}
