package com.adimustbefunny.cinema.controller;

import com.adimustbefunny.cinema.model.Client;
import com.adimustbefunny.cinema.service.ClientRestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping(path = "/client")
public class ClientController {

    private final ClientRestService clientRestService;

    @GetMapping(path = "/create")
    public String getClientCreateForm(Model model){
        model.addAttribute("client",new Client());

        return "client_form";
    }

    @GetMapping(path = "/list")
    public String getClientList(Model model){
        model.addAttribute("clients", clientRestService.getAllClients());

        return "client_list";
    }

    @PostMapping(path = "/create")
    public String postCreateClient(Client client){
        clientRestService.save(client);

        return "redirect:/client/list";
    }

}
