package com.adimustbefunny.cinema.controller.rest;

import com.adimustbefunny.cinema.model.Film;
import com.adimustbefunny.cinema.service.FilmRestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/film")
@RequiredArgsConstructor
public class FilmRestController {

    private final FilmRestService filmRestService;

    @PostMapping
    public ResponseEntity createFilm(@RequestBody Film film){

        if(film.getTitle() == null || film.getDescription() == null || film.getDuration() == null)
        return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build();

        System.out.println(film);
        filmRestService.save(film);
        System.out.println(film);

        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
    @GetMapping
    public Film getFilmById(@RequestParam(name = "id")Long id){
        return filmRestService.getFilmById(id);
    }
    @DeleteMapping
    public ResponseEntity deleteFilmById(@RequestParam(name = "id")Long id){

        filmRestService.deleteById(id);

        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
    @PutMapping
    public ResponseEntity updateFilm(@RequestBody Film film){

        if(film.getId() == null || film.getTitle() == null || film.getDescription() == null || film.getDuration() == null)
            return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build();

        filmRestService.save(film);

        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

}
