package com.adimustbefunny.cinema.service;

import com.adimustbefunny.cinema.model.Client;
import com.adimustbefunny.cinema.model.FilmInstance;
import com.adimustbefunny.cinema.model.Seat;
import com.adimustbefunny.cinema.repository.SeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class SeatService {
    private final SeatRepository seatRepository;

    public void save(Seat seat){
        seatRepository.save(seat);
    }
    public void saveMany(List<Seat> seatList){
        seatRepository.saveAll(seatList);
    }

    public Seat findSeatById(Long id){
        return seatRepository.findById(id).orElseGet(new Supplier<Seat>() {
            @Override
            public Seat get() {
                return new Seat();
            }
        });
    }

    public List<Seat> findSeatsByFilmInstance(FilmInstance filmInstance)
    {
        return seatRepository.findByFilmInstance(filmInstance);
    }

    public void deleteSeatById(Long id){
        seatRepository.deleteById(id);
    }
    public void deleteSeatByFilmInstance(FilmInstance filmInstance){
        seatRepository.deleteByFilmInstance(filmInstance);
    }
    public void deleteSeatByFilmInstanceId(Long filmInstanceId){
        seatRepository.deleteByFilmInstanceId(filmInstanceId);
    }

    public List<Seat> findSeatsByClientId(Client client) {
        return seatRepository.findByClient(client);
    }
}
