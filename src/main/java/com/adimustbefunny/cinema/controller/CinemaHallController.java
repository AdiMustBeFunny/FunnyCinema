package com.adimustbefunny.cinema.controller;


import com.adimustbefunny.cinema.model.CinemaHall;
import com.adimustbefunny.cinema.service.CinemaHallService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping(path = "/cinemahall")
public class CinemaHallController {

    private final CinemaHallService cinemaHallService;

    @GetMapping(path = "/list")
    public String getCinemaHallList(Model model){
        model.addAttribute("cinemaHalls",cinemaHallService.getAllCinemaHalls());

        return "cinema_hall_list";
    }

    @GetMapping(path = "/create")
    public String getCinemaHallForm(Model model){
        model.addAttribute("cinemaHall",new CinemaHall());

        return "cinema_hall_form";
    }

    @PostMapping(path = "/create")
    public String postCinemaHall(CinemaHall cinemaHall){
        cinemaHallService.save(cinemaHall);

        return "redirect:/cinemahall/list";
    }

}
