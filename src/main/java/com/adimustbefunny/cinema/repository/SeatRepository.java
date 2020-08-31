package com.adimustbefunny.cinema.repository;

import com.adimustbefunny.cinema.model.FilmInstance;
import com.adimustbefunny.cinema.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat,Long> {

    void deleteByFilmInstance(FilmInstance filmInstance);
    void deleteByFilmInstanceId(Long filmInstanceId);
    List<Seat> findByFilmInstance(FilmInstance filmInstance);
}
