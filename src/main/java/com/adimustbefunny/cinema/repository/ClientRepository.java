package com.adimustbefunny.cinema.repository;

import com.adimustbefunny.cinema.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client,Long> {
}
