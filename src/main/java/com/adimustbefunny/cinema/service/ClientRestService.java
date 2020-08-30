package com.adimustbefunny.cinema.service;


import com.adimustbefunny.cinema.model.Client;
import com.adimustbefunny.cinema.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class ClientRestService {

    private final ClientRepository clientRepository;

    public void save(Client client){
        clientRepository.save(client);
    }

    public void deleteClientById(Long id){
        clientRepository.deleteById(id);
    }

    public Client getClientById(Long id){
        return clientRepository.findById(id).orElseGet(new Supplier<Client>() {
            @Override
            public Client get() {
                return new Client();
            }
        });
    }

}
