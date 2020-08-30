package com.adimustbefunny.cinema.repository;

import com.adimustbefunny.cinema.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmRepository extends JpaRepository<Film,Long> {
}
