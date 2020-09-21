package com.adimustbefunny.cinema.controller;


import com.adimustbefunny.cinema.model.*;
import com.adimustbefunny.cinema.model.dto.CheckoutDTO;
import com.adimustbefunny.cinema.model.dto.CreateFormFilmInstanceDTO;
import com.adimustbefunny.cinema.model.dto.FilmInstanceDetailsDTO;
import com.adimustbefunny.cinema.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping(path = "/filminstance")
public class FilmInstanceController {

    private final FilmInstanceService filmInstanceService;
    private final CinemaHallService cinemaHallService;
    private final FilmRestService filmRestService;
    private final SeatService seatService;
    private final ClientRestService clientRestService;

    @PostMapping(path = "/create")
    public String postCreateFormFilmInstance(CreateFormFilmInstanceDTO createFormFilmInstanceDTO){

        System.out.println(createFormFilmInstanceDTO);
        LocalDate localDate = LocalDate.parse(createFormFilmInstanceDTO.getStartDate());
        System.out.println(localDate);
        LocalDateTime dateTime = LocalDateTime.parse(createFormFilmInstanceDTO.getStartDate()+"T"+
                (createFormFilmInstanceDTO.getHour().toString().length()==1 ? "0"+createFormFilmInstanceDTO.getHour():createFormFilmInstanceDTO.getHour() )+
                ":"+(createFormFilmInstanceDTO.getMinute().toString().length()==1 ? "0"+createFormFilmInstanceDTO.getMinute():createFormFilmInstanceDTO.getMinute()));

        FilmInstance filmInstance = new FilmInstance();
        Film film;
        CinemaHall cinemaHall;


        film = filmRestService.getFilmById(createFormFilmInstanceDTO.getFilmId());
        cinemaHall = cinemaHallService.getCinemaHallById(createFormFilmInstanceDTO.getCinemaHallId());

        System.out.println(cinemaHall);

        filmInstance.setDate(dateTime);
        filmInstance.setCinemaHall(cinemaHall);
        filmInstance.setFilm(film);

        List<FilmInstance> overlappingFilmInstances = filmInstanceService.findAllFilmInstancesOverlappingWithGivenFilmInstance(filmInstance);

        System.out.println(overlappingFilmInstances.size());
        System.out.println(overlappingFilmInstances);

        filmInstanceService.create(filmInstance);
        System.out.println(filmInstance);

        System.out.println(dateTime);

        return "redirect:/filminstance/list";
    }

    @GetMapping(path = "/create")
    public String getCreateFormFilmInstance(Model model){

        model.addAttribute("cinemaHalls",cinemaHallService.getAllCinemaHalls());
        model.addAttribute("films",filmRestService.findAllFilms());
        model.addAttribute("createFormFilmInstanceDTO",new CreateFormFilmInstanceDTO());

        return "film_instance_create_form";
    }

    @GetMapping(path = "/list")
    public String getFilmInstances(Model model){

        List<FilmInstance> filmInstances = filmInstanceService.findAllFilmInstances();
        filmInstances.forEach(filmInstance -> {

        });

        List<FilmInstanceDetailsDTO> filmInstanceDetailsDTOs = filmInstances.stream().map( filmInstance -> {
            FilmInstanceDetailsDTO filmInstanceDetailsDTO = new FilmInstanceDetailsDTO();

            String startTime = filmInstance.getDate().getHour()+":"+filmInstance.getDate().getMinute();
            String cinemaHallName = filmInstance.getCinemaHall().getTitle();
            String endTime = filmInstance.getDate().plusMinutes(filmInstance.getFilm().getDuration()).getHour()+":"+
                    filmInstance.getDate().plusMinutes(filmInstance.getFilm().getDuration()).getMinute();
            String filmTitle = filmInstance.getFilm().getTitle();
            String date = filmInstance.getDate().getYear()+"-"+filmInstance.getDate().getMonthValue()+"-"+filmInstance.getDate().getDayOfMonth();
            Integer duration = filmInstance.getFilm().getDuration();


            filmInstanceDetailsDTO.setCinemaHallName(cinemaHallName);
            filmInstanceDetailsDTO.setStartTime(startTime);
            filmInstanceDetailsDTO.setEndTime(endTime);
            filmInstanceDetailsDTO.setFilmTitle(filmTitle);
            filmInstanceDetailsDTO.setDate(date);
            filmInstanceDetailsDTO.setFilmDuration(duration);
            filmInstanceDetailsDTO.setId(filmInstance.getId());


            return filmInstanceDetailsDTO;
        }).collect(Collectors.toList());

        model.addAttribute("filmInstances",filmInstanceDetailsDTOs);

        return "film_instance_list";
    }

    @GetMapping(path = "/details")
    public String getViewSeats(Model model, @RequestParam(name = "id")Long id, HttpSession session){

        FilmInstance filmInstance = filmInstanceService.findFilmInstanceById(id);
        List<Client> clients = clientRestService.getAllClients();
        List<Seat> seats = seatService.findSeatsByFilmInstance(filmInstance);

        model.addAttribute("filmInstance",filmInstance);
        model.addAttribute("clients",clients);
        model.addAttribute("seats",seats);
        model.addAttribute("userId",session.getAttribute("userId"));
        return "film_instance_seats";
    }

    @GetMapping(path = "/checkout")
    public String getCheckout(Model model, @RequestParam(name="filminstanceId")Long filminstanceId,
                              @RequestParam(name="clientId")Long clientId){

        Client client = clientRestService.getClientById(clientId);
        List<Seat> seats = seatService.findSeatsByClientId(client);
        FilmInstance filmInstance = filmInstanceService.findFilmInstanceById(filminstanceId);

        model.addAttribute("client",client);
        model.addAttribute("seats",seats);
        model.addAttribute("filmInstance",filmInstance);
        model.addAttribute("totalPrice",seats.size()*5);
        CheckoutDTO checkoutDTO = new CheckoutDTO(clientId,filminstanceId);
        model.addAttribute("checkoutDTO",checkoutDTO);

        return "checkout";
    }

    @PostMapping(path = "/checkout")
    public String postCheckout(CheckoutDTO checkoutDTO){

        List<Seat> seats = seatService.findSeatsByClientId(clientRestService.getClientById(checkoutDTO.getClientId()));
        seats.forEach(seat -> seat.setBought(true));
        seatService.saveMany(seats);

        return "redirect:/filminstance/scam";
    }

    @GetMapping(path = "/scam")
    public String getScam(){

        return "scam";
    }
}
