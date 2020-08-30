package com.adimustbefunny.cinema.controller.rest;


import com.adimustbefunny.cinema.model.Client;
import com.adimustbefunny.cinema.service.ClientRestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/client")
public class ClientRestController {

    private final ClientRestService clientRestService;

    @PostMapping
    ResponseEntity createClient(@RequestBody Client client){

        if(client.getPassword() == null || client.getUsername() == null)
            return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build();

        clientRestService.save(client);

        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
    @GetMapping
    Client getClientById(@RequestParam(name = "id")Long id){
        return clientRestService.getClientById(id);
    }
    @DeleteMapping
    ResponseEntity deleteClientById(@RequestParam(name = "id")Long id){
        clientRestService.deleteClientById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
    @PutMapping
    ResponseEntity updateClient(@RequestBody Client client){

        if(client.getId() == null || client.getPassword() == null || client.getUsername() == null)
            return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build();

        clientRestService.save(client);

        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }


}
