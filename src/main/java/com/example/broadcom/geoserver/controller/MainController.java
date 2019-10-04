package com.example.broadcom.geoserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.broadcom.geonames.ws.model.SearchResult;
import com.example.broadcom.geoserver.dto.SearchQueryDto;
import com.example.broadcom.geoserver.service.GeoNamesService;

@Controller
public class MainController {

    @Autowired
    private GeoNamesService geoNamesService;

    @GetMapping("/")
    public String main(Model model,SearchQueryDto searchInput) {

        model.addAttribute("searchInput" , searchInput);
        model.addAttribute("languages", geoNamesService.getLanguages());
        return "welcome"; //view
    }

    @PostMapping("/search")
    public String search(Model model, SearchQueryDto searchInput) {
        SearchResult searchResult = geoNamesService.search(searchInput);
        model.addAttribute("searchInput", searchInput);
        model.addAttribute("searchResult", searchResult);
        return "searchResult"; //view
    }

}
