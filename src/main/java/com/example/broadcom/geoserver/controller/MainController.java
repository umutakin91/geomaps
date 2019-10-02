package com.example.broadcom.geoserver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.broadcom.geoserver.dto.SearchQueryDto;

@Controller
public class MainController {

    @GetMapping("/")
    public String main(Model model,SearchQueryDto searchInput) {

        model.addAttribute("searchInput" , searchInput);
        return "welcome"; //view
    }

}
