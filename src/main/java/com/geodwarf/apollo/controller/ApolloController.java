package com.geodwarf.apollo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ApolloController {

    @GetMapping("/greeting")
    public String greeting(){
        return  "greeting from here";
    }

    @GetMapping("/")
    public String noPathEntry(){
        return  "home";
    }

    @GetMapping("/login")
    public String login(){
        return  "logged-in";
    }
}
