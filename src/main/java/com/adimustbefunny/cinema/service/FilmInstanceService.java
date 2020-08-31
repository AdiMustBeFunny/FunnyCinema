package com.adimustbefunny.cinema.service;

import com.adimustbefunny.cinema.model.FilmInstance;
import com.adimustbefunny.cinema.repository.FilmInstanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class FilmInstanceService {

    private final FilmInstanceRepository filmInstanceRepository;

    public void save(FilmInstance filmInstance) {
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
        filmInstanceRepository.deleteById(id);
    }
}
