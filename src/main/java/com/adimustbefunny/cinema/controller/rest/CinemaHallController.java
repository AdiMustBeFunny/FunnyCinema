package com.adimustbefunny.cinema.controller.rest;


import com.adimustbefunny.cinema.model.CinemaHall;
import com.adimustbefunny.cinema.service.CinemaHallService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/cinemahall")
@RequiredArgsConstructor
public class CinemaHallController {

    private final CinemaHallService cinemaHallService;


    @PostMapping
    ResponseEntity createcinemaHall(@RequestBody CinemaHall cinemaHall){

        if(cinemaHall.getTitle() == null || cinemaHall.getSeatCount() == null)
            return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build();

        cinemaHallService.save(cinemaHall);

        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
    @GetMapping
    CinemaHall getCinemaHallById(@RequestParam(name = "id")Long id){
        return cinemaHallService.getCinemaHallById(id);
    }
    @DeleteMapping
    ResponseEntity deleteClientById(@RequestParam(name = "id")Long id){
        cinemaHallService.deleteCinemaHallById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
    @PutMapping
    ResponseEntity updateCinemaHall(@RequestBody CinemaHall cinemaHall){

        if(cinemaHall.getId() == null || cinemaHall.getTitle() == null || cinemaHall.getSeatCount() == null)
            return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build();

        cinemaHallService.save(cinemaHall);

        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }


}
