package com.adimustbefunny.cinema.repository;

import com.adimustbefunny.cinema.model.FilmInstance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmInstanceRepository extends JpaRepository<FilmInstance,Long> {
}
