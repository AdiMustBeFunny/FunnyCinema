package com.adimustbefunny.cinema.service;


import com.adimustbefunny.cinema.model.CinemaHall;
import com.adimustbefunny.cinema.model.Client;
import com.adimustbefunny.cinema.repository.CinemaHallRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class CinemaHallService {

    private final CinemaHallRepository cinemaHallRepository;

    public void save(CinemaHall client){
        cinemaHallRepository.save(client);
    }

    public void deleteCinemaHallById(Long id){
        cinemaHallRepository.deleteById(id);
    }

    public CinemaHall getCinemaHallById(Long id){
        return cinemaHallRepository.findById(id).orElseGet(new Supplier<CinemaHall>() {
            @Override
            public CinemaHall get() {
                return new CinemaHall();
            }
        });
    }

}
