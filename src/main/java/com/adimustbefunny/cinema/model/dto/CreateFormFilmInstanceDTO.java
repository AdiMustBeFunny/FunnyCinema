package com.adimustbefunny.cinema.model.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateFormFilmInstanceDTO {

    private Long cinemaHallId;
    private Long filmId;
    private String startDate;
    private Integer hour;
    private Integer minute;

}
