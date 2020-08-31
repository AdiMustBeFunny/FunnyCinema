package com.adimustbefunny.cinema.controller.rest;


import com.adimustbefunny.cinema.model.CinemaHall;
import com.adimustbefunny.cinema.model.Film;
import com.adimustbefunny.cinema.model.FilmInstance;
import com.adimustbefunny.cinema.model.dto.FilmInstanceDTO;
import com.adimustbefunny.cinema.service.CinemaHallService;
import com.adimustbefunny.cinema.service.FilmInstanceService;
import com.adimustbefunny.cinema.service.FilmRestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/filminstance")
public class FilmInstanceRestController {

    private final FilmRestService filmRestService;
    private final CinemaHallService cinemaHallService;
    private final FilmInstanceService filmInstanceService;

    @PostMapping
    public ResponseEntity createFilmInstance(@RequestBody FilmInstanceDTO filmInstanceDTO){

        if(filmInstanceDTO.getCinema_hall_id()==null || filmInstanceDTO.getFilm_id()==null || filmInstanceDTO.getDate() == null)
            return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build();

        Film film = filmRestService.getFilmById(filmInstanceDTO.getFilm_id());
        CinemaHall cinemaHall = cinemaHallService.getCinemaHallById(filmInstanceDTO.getCinema_hall_id());

        FilmInstance filmInstance = new FilmInstance();
        filmInstance.setFilm(film);
        filmInstance.setCinemaHall(cinemaHall);
        filmInstance.setDate(filmInstanceDTO.getDate());

        System.out.println(filmInstanceDTO);
        System.out.println(film);
        System.out.println(cinemaHall);

        filmInstanceService.save(filmInstance);




        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @PutMapping
    public ResponseEntity updateFilmInstance(@RequestBody FilmInstanceDTO filmInstanceDTO){

        if(filmInstanceDTO.getId()==null || filmInstanceDTO.getCinema_hall_id()==null || filmInstanceDTO.getFilm_id()==null || filmInstanceDTO.getDate() == null)
            return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build();

        Film film = filmRestService.getFilmById(filmInstanceDTO.getFilm_id());
        CinemaHall cinemaHall = cinemaHallService.getCinemaHallById(filmInstanceDTO.getCinema_hall_id());

        FilmInstance filmInstance = new FilmInstance();
        filmInstance.setId(filmInstanceDTO.getId());
        filmInstance.setFilm(film);
        filmInstance.setCinemaHall(cinemaHall);
        filmInstance.setDate(filmInstanceDTO.getDate());

        filmInstanceService.save(filmInstance);

        return ResponseEntity.status(HttpStatus.ACCEPTED).build();

    }

    @GetMapping
    public FilmInstance getFilmInstanceById(@RequestParam(name = "id")Long id){
        return filmInstanceService.findFilmInstanceById(id);
    }

    @DeleteMapping
    public void deleteFilmInstanceById(@RequestParam(name="id")Long id){
        filmInstanceService.deleteFilmInstanceById(id);
    }



}
