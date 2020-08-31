package com.adimustbefunny.cinema.service;

import com.adimustbefunny.cinema.model.FilmInstance;
import com.adimustbefunny.cinema.model.Seat;
import com.adimustbefunny.cinema.repository.FilmInstanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class FilmInstanceService {

    private final FilmInstanceRepository filmInstanceRepository;
    private final SeatService seatService;

    public void create(FilmInstance filmInstance) {
        filmInstanceRepository.save(filmInstance);

        List<Seat> seats = new ArrayList<>();

        for(int i=0;i<filmInstance.getCinemaHall().getSeatCount();i++){
            Seat seat = new Seat();
            seat.setFilmInstance(filmInstance);
            seats.add(seat);
        }

        seatService.saveMany(seats);

    }

    public void update(FilmInstance filmInstance) {
        filmInstanceRepository.save(filmInstance);
    }

    public FilmInstance findFilmInstanceById(Long id) {
        return filmInstanceRepository.findById(id).orElseGet(new Supplier<FilmInstance>() {
            @Override
            public FilmInstance get() {
                return new FilmInstance();
            }
        });
    }

    public void deleteFilmInstanceById(Long id) {
//        FilmInstance filmInstance = findFilmInstanceById(id);
//        System.out.println(filmInstance);
//        seatService.deleteSeatByFilmInstance(filmInstance);
        seatService.deleteSeatByFilmInstanceId(id);
        filmInstanceRepository.deleteById(id);
    }
}
