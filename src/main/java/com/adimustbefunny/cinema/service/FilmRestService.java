package com.adimustbefunny.cinema.service;

import com.adimustbefunny.cinema.model.Film;
import com.adimustbefunny.cinema.repository.FilmRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class FilmRestService {

    private final FilmRepository filmRepository;

    public void save(Film film){
        filmRepository.save(film);
    }

    public void deleteById(Long id){
        filmRepository.deleteById(id);
    }

    public Film getFilmById(Long id){
        return filmRepository.findById(id).orElseGet(new Supplier<Film>() {
            @Override
            public Film get() {
                return new Film();
            }
        });
    }


    public List<Film> findAllFilms() {
        return filmRepository.findAll();
    }

    public List<Film> getAllFilms() {
        return filmRepository.findAll();
    }
}
