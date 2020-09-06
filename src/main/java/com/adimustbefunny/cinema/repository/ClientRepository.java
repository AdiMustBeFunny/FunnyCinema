package com.adimustbefunny.cinema.repository;

import com.adimustbefunny.cinema.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client,Long> {

    Optional<Client> findByUsername(String username);

}
