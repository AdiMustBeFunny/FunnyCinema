package com.adimustbefunny.cinema.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Main {


    public static void main(String[] args) {

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        System.out.println(bCryptPasswordEncoder.encode("123"));


    }

}
