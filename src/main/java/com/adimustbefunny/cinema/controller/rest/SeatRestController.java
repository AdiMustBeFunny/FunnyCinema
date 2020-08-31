package com.adimustbefunny.cinema.controller.rest;


import com.adimustbefunny.cinema.model.Seat;
import com.adimustbefunny.cinema.service.FilmInstanceService;
import com.adimustbefunny.cinema.service.SeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/seat")
public class SeatRestController {

    private final SeatService seatService;
    private final FilmInstanceService filmInstanceService;

    @GetMapping
    public List<Seat> getSeatsByFilmInstanceId(@RequestParam(name="filmInstanceId") Long filmInstanceId){
        return seatService.findSeatsByFilmInstance(filmInstanceService.findFilmInstanceById(filmInstanceId));
    }

}
