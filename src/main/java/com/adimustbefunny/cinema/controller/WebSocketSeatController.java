package com.adimustbefunny.cinema.controller;

import com.adimustbefunny.cinema.model.Client;
import com.adimustbefunny.cinema.model.Seat;
import com.adimustbefunny.cinema.model.dto.SeatWebSocketReceiveDTO;
import com.adimustbefunny.cinema.model.dto.SeatWebSocketResponseDTO;
import com.adimustbefunny.cinema.service.ClientRestService;
import com.adimustbefunny.cinema.service.SeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class WebSocketSeatController {

    private final SeatService seatService;
    private final ClientRestService clientRestService;

    @MessageMapping("/update")
    @SendTo("/cinema/seats")
    SeatWebSocketResponseDTO processMessage(SeatWebSocketReceiveDTO seatWebSocketReceiveDTO){

        SeatWebSocketResponseDTO seatWebSocketResponseDTO = new SeatWebSocketResponseDTO();
        seatWebSocketResponseDTO.setSeatId(seatWebSocketReceiveDTO.getSeatId());

        System.out.println(seatWebSocketReceiveDTO);

        Client client = clientRestService.getClientById(seatWebSocketReceiveDTO.getClientId());
        Seat seat = seatService.findSeatById(seatWebSocketReceiveDTO.getSeatId());

        System.out.println(client);
        System.out.println(seat);

        if (client.equals( seat.getClient()) ) {
            seat.setClient(null);
            seatService.save(seat);

            seatWebSocketResponseDTO.setSeatIsFree(true);
        }
        else if(seat.getClient() == null){
            seat.setClient(client);
            seatService.save(seat);

            seatWebSocketResponseDTO.setSeatIsFree(false);
        }

            return seatWebSocketResponseDTO;

    }

}
