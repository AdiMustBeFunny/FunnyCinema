package com.adimustbefunny.cinema.controller;

import com.adimustbefunny.cinema.model.Film;
import com.adimustbefunny.cinema.service.FilmRestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping(path="/film")
public class FilmController {

    private final FilmRestService filmRestService;

    @GetMapping(path = "/create")
    public String getCreateFilmForm(Model model){
        model.addAttribute("film",new Film());

        return "film_create_form";
    }

    @GetMapping(path = "/list")
    public String getFilmList(Model model){
        model.addAttribute("films",filmRestService.getAllFilms());

        return "film_list";
    }

    @PostMapping(path = "/create")
    public String postCreateFilm(Film film){

        if(film.getDuration() != null && film.getDescription() != null && film.getTitle() != null && film.getId() == null)
            filmRestService.save(film);

        return "redirect:/film/list";
    }


}
