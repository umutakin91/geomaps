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
    public String indexPage() {
        return "index";
    }

    @GetMapping("/search")
    public String searchGet(Model model, @ModelAttribute("searchQueryDto") SearchQueryDto searchQueryDto) {

        model.addAttribute("searchQueryDto" , searchQueryDto);
        model.addAttribute("languages", geoNamesService.getLanguages());
        return "search"; //view
    }

    @PostMapping("/search")
    public String searchPost(Model model, @ModelAttribute("searchQueryDto") @Valid SearchQueryDto searchQueryDto,
                         BindingResult bindingResult) {

        if (bindingResult.hasErrors()){
            model.addAttribute("languages", geoNamesService.getLanguages());
            return "search";
        }

        logSearch(searchQueryDto);

        SearchResult searchResult = geoNamesService.search(searchQueryDto);
        model.addAttribute("searchQueryDto", searchQueryDto);
        model.addAttribute("searchResult", searchResult);
        return "searchResult"; //view
    }

    private void logSearch(SearchQueryDto searchQueryDto) {
        LOGGER.info("Request received to search query, q = [{}] , lang = [{}]",
                searchQueryDto.getQ(),
                searchQueryDto.getLang());
    }
}
