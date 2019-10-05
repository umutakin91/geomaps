package com.example.broadcom.geoserver.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestClientException;

import com.example.broadcom.geonames.ws.model.SearchResult;
import com.example.broadcom.geoserver.dto.SearchQueryDto;
import com.example.broadcom.geoserver.service.GeoNamesService;
import com.example.broadcom.geoserver.ws.rest.RestDefaultClientImpl;

@Controller
public class MainController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestDefaultClientImpl.class);

    @Autowired
    private GeoNamesService geoNamesService;

    @GetMapping("/")
    public String main(Model model, SearchQueryDto searchInput) {

        model.addAttribute("searchInput" , searchInput);
        model.addAttribute("languages", geoNamesService.getLanguages());
        return "welcome"; //view
    }

    @PostMapping("/")
    public String search(Model model, @ModelAttribute("searchInput") @Valid SearchQueryDto searchInput, BindingResult bingBindingResult) {

        if (bingBindingResult.hasErrors()){
            model.addAttribute("languages", geoNamesService.getLanguages());
            return "welcome";
        }

        logSearch(searchInput);

        SearchResult searchResult = geoNamesService.search(searchInput);
        model.addAttribute("searchInput", searchInput);
        model.addAttribute("searchResult", searchResult);
        return "searchResult"; //view
    }

    private void logSearch(SearchQueryDto searchInput) {
        LOGGER.info("Request received to search query, q = [{}] , lang = [{}]",
                searchInput.getQ(),
                searchInput.getLang());
    }
}
