package com.adimustbefunny.cinema.controller;


import com.adimustbefunny.cinema.model.Client;
import com.adimustbefunny.cinema.model.ClientDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@SessionAttributes({"currentUser"})
public class LoginController {


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(HttpSession session) {

        if(session.getAttribute("userId")!=null){
            System.out.println("User is already logged in");
            return "redirect:/";
        }

        return "login";
    }

    @RequestMapping(value = "/loginFailed", method = RequestMethod.GET)

    public String loginError(Model model) {
        model.addAttribute("error", "true");
        return "login";
    }


    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(SessionStatus sessionStatus,HttpSession session) {

        SecurityContextHolder.getContext().setAuthentication(null);

        session.removeAttribute("userId");
        sessionStatus.setComplete();

        return "redirect:/login";

    }

    @RequestMapping(value = "/postLogin",method = RequestMethod.POST)
    public String postLogin(Model model, HttpSession session){
        System.out.println("post login");

        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        validatePrinciple(authentication.getPrincipal());

        Client loggedInUser = ((ClientDetails) authentication.getPrincipal()).getClient();

        session.setAttribute("userId",loggedInUser.getId());

        return "redirect:/";
    }

    private void validatePrinciple(Object principal) {
        if (!(principal instanceof ClientDetails)) {
            throw new  IllegalArgumentException("Principal can not be null!");
        }
    }

}
