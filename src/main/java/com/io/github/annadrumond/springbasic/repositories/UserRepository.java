package com.io.github.annadrumond.springbasic.repositories;

import com.io.github.annadrumond.springbasic.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository<User,Long> - entidade,chave primária
public interface UserRepository extends JpaRepository<User,Long> {



}
