package com.adimustbefunny.cinema.model.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilmInstanceDetailsDTO {
    private String startTime;
    private String endTime;
    private String filmTitle;
    private String cinemaHallName;
    private Integer filmDuration;
}
