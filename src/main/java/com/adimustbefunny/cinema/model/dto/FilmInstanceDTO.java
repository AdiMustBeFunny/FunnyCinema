package com.adimustbefunny.cinema.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class FilmInstanceDTO {
    private Long id;
    private Long cinema_hall_id;
    private Long film_id;
    private LocalDateTime date;
}
