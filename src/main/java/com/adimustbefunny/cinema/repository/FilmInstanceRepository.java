package com.adimustbefunny.cinema.repository;

import com.adimustbefunny.cinema.model.CinemaHall;
import com.adimustbefunny.cinema.model.FilmInstance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface FilmInstanceRepository extends JpaRepository<FilmInstance,Long> {
    List<FilmInstance> findByCinemaHallAndDateBetween(CinemaHall cinemaHall, LocalDateTime start,LocalDateTime end);
    List<FilmInstance> findByCinemaHall(CinemaHall cinemaHall);
//    @Query("SELECT f FROM FilmInstance WHERE f.")
//    List<FilmInstance> findByCinemaHallAndDateBetween(CinemaHall cinemaHall, LocalDateTime start,LocalDateTime end);

}
