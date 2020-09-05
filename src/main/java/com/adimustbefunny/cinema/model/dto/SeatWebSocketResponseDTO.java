package com.adimustbefunny.cinema.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeatWebSocketResponseDTO {
    private Long seatId;
    private Boolean seatIsFree;
}
