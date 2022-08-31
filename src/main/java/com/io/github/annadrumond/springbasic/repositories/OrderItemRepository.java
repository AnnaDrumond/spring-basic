package com.io.github.annadrumond.springbasic.repositories;

import com.io.github.annadrumond.springbasic.entities.OrderItem;
import com.io.github.annadrumond.springbasic.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositório <a href="https://www.alura.com.br/artigos/primeiros-passos-spring">...</a>
 * Quando pensamos em inserir, pesquisar, alterar dados, logo pensamos em DAOS ou até mesmo em comandos SQL,
 * isto é muito trabalhoso. Mas então como vamos fazer a persistência dos dados? Na classe de entidade?
 * Iremos fazer isto nos repositórios, pois facilita demais o processo do CRUD.
 * Por boas práticas não vamos fazer dentro da classe entidade.
 * O repositório tem uma interface chamada CrudRepository que nos permite fazer um CRUD dos nossos dados, sem que a gente escreva uma linha de código.
 */
// JpaRepository<User,Long> - entidade,chave primária
// Não preciso colocar o @Repository pois a minha interface já extends JpaRepository
public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {



}
