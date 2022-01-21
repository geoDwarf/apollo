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

    @GetMapping("restr/regional-maps")
    public String getRegionalMaps(){
        return  "regional-maps";
    }

    @GetMapping("restr/dungeon-maps")
    public String getDungeonMaps(){
        return  "dungeon-maps";
    }

    @GetMapping("restr/city-maps")
    public String getCityMaps(){
        return  "city-maps";
    }
}
