package com.io.github.annadrumond.springbasic.configurations;

import com.io.github.annadrumond.springbasic.entities.*;
import com.io.github.annadrumond.springbasic.entities.enums.OrderStatus;
import com.io.github.annadrumond.springbasic.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.Instant;
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
    @Autowired // seria similar ao @Inject do Context and Dependency Injection (CDI)
    private UserRepository userRepository;

    @Autowired // seria similar ao @Inject do Context and Dependency Injection (CDI)
    private CategoryRepository categoryRepository;

    @Autowired // seria similar ao @Inject do Context and Dependency Injection (CDI)
    private OrderRepository orderRepository;

    @Autowired // seria similar ao @Inject do Context and Dependency Injection (CDI)
    private ProductRepository productRepository;

    @Autowired // seria similar ao @Inject do Context and Dependency Injection (CDI)
    private OrderItemRepository orderItemRepository;

    //Agora preciso garantir que a bd seja populada assim que o meu programa começar a correr
    //Neste exemplo vamos fazer a classe implementar a interface ComandLineRunner
    //Isso obriga a implmentação do método run() - tudo que estiver dentro deste método será executado quando a app for iniciada
    @Override
    public void run(String... args) throws Exception {

        //não estou mandando o id, pois será gerado pela bd
        Category cat1 = new Category("Electronics");
        Category cat2 = new Category( "Books");
        Category cat3 = new Category("Computers");
        categoryRepository.saveAll(Arrays.asList(cat1,cat2,cat3));

        Product product1 = new Product( "The Lord of the Rings", "Lorem ipsum dolor sit amet, consectetur.", 90.5, "");
        Product product2 = new Product( "Smart TV", "Nulla eu imperdiet purus. Maecenas ante.", 2190.0, "");
        Product product3 = new Product( "Macbook Pro", "Nam eleifend maximus tortor, at mollis.", 1250.0, "");
        Product product4 = new Product( "PC Gamer", "Donec aliquet odio ac rhoncus cursus.", 1200.0, "");
        Product product5 = new Product( "Rails for Dummies", "Cras fringilla convallis sem vel faucibus.", 100.99, "");
        productRepository.saveAll(Arrays.asList(product1,product2,product3,product4,product5));

        //Fazer algumas associações
        product1.getCategories().add(cat2);
        product2.getCategories().add(cat1);
        product2.getCategories().add(cat3);
        product3.getCategories().add(cat3);
        product4.getCategories().add(cat3);
        product5.getCategories().add(cat2);
        //salvar as entidades produtos, agora com as ssociações que foram inseridas
        productRepository.saveAll(Arrays.asList(product1,product2,product3,product4,product5));

        //não estou mandando o id, pois será gerado pela bd
        User user1 = new User("Maria Brown", "maria@gmail.com", "988888888", "123456");
        User user2 = new User("Alex Green", "alex@gmail.com", "977777777", "123456");
        //O saveAll é um método Java da interface JpaRepository
        userRepository.saveAll(Arrays.asList(user1,user2));

        //Se eu quiser a data do sistema chamo o Instant.now()
        //A data/hora estrá em UTC
        Order order1 = new Order( Instant.now(), OrderStatus.PAID, user1);
        Order order2 = new Order( Instant.parse("2019-07-21T03:42:10Z"), OrderStatus.WAITING_PAYMENT,user2);
        Order order3 = new Order( Instant.parse("2019-07-22T15:21:22Z"), OrderStatus.SHIPPED, user1);
        orderRepository.saveAll(Arrays.asList(order1,order2,order3));

        OrderItem orderItem1 = new OrderItem(order1, product1, 2, product1.getPrice());
        OrderItem orderItem2 = new OrderItem(order1, product3, 1, product3.getPrice());
        OrderItem orderItem3 = new OrderItem(order2, product3, 2, product3.getPrice());
        OrderItem orderItem4 = new OrderItem(order3, product5, 2, product5.getPrice());
        orderItemRepository.saveAll(Arrays.asList(orderItem1,orderItem2,orderItem3,orderItem4));

        Payment payment1 = new Payment(Instant.parse("2019-07-21T21:42:10Z"), order1);
        //Para salvar o objeto dependente ( que não é o dono da relação) em um OneToOne, não chamo o repository do próprio objeto e sim,
        //Vamos fazer a associação em memória e depois, salvar de novo o objeto dono da relação
        order1.setPayment(payment1);
        orderRepository.save(order1);
    }
}
