package com.adimustbefunny.cinema.service;

import com.adimustbefunny.cinema.model.Client;
import com.adimustbefunny.cinema.model.ClientDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private ClientRestService clientRestService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Client client = clientRestService.getClientByUSername(username);

        if(client == null){
            throw new UsernameNotFoundException("User not found");
        }

        return new ClientDetails(client);
    }
}
