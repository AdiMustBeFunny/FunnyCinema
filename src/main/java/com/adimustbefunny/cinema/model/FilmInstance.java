package com.adimustbefunny.cinema.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilmInstance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    LocalDate date;

    @ManyToOne
    private CinemaHall cinemaHall;

    @ManyToOne
    private Film film;

    @OneToMany(mappedBy = "filmInstance")
    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Seat> seats;
}
