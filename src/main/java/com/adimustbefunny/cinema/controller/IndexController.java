package com.adimustbefunny.cinema.controller;


import com.adimustbefunny.cinema.service.FilmInstanceService;
import com.adimustbefunny.cinema.service.FilmRestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
@RequestMapping(path = "/")
public class IndexController {

    private final FilmRestService filmRestService;
    private final FilmInstanceService filmInstanceService;

    @GetMapping(path = "/")
    public String getIndexPage(Model model){

        model.addAttribute("films",filmRestService.findAllFilms());
        model.addAttribute("filmInstances",filmInstanceService.findFilmInstancesBetweenDates(LocalDateTime.now(),LocalDateTime.now().plusDays(7)));

        return "index";
    }

}
