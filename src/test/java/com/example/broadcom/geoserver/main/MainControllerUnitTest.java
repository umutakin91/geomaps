package com.example.broadcom.geoserver.main;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.util.ArrayList;
import java.util.List;
import com.example.broadcom.geonames.ws.model.GeoNames;
import com.example.broadcom.geonames.ws.model.SearchResult;
import com.example.broadcom.geoserver.controller.MainController;
import com.example.broadcom.geoserver.dto.SearchQueryDto;
import com.example.broadcom.geoserver.service.GeoNamesService;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Main Controller test to get status OK.
 *
 * @author Umut AKIN
 */
@RunWith(SpringRunner.class)
@WebMvcTest(controllers = MainController.class)
public class MainControllerUnitTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GeoNamesService geoNamesService;

    @Test
    public void whenValid_thenReturns200() throws Exception {

        mockMvc.perform(get("/"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void whenValidSearchGet_thenReturns200() throws Exception {

        mockMvc.perform(get("/search"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void whenValidSearchPost_thenReturns200() throws Exception {

        SearchQueryDto searchQueryDto = prepareSearchQueryDto();
        when(geoNamesService.search(searchQueryDto)).thenReturn(expectedSearchResult());

        mockMvc.perform(post("/search")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(searchQueryDto)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    private SearchQueryDto prepareSearchQueryDto() {
        SearchQueryDto searchQueryDto = new SearchQueryDto();
        searchQueryDto.setLang("en");
        searchQueryDto.setQ("Turkish");
        searchQueryDto.setStartRow(2);
        return searchQueryDto;
    }

    private SearchResult expectedSearchResult() {
        SearchResult searchResult = new SearchResult();
        searchResult.setTotalResultsCount(10);
        searchResult.setNextStartRow(4);
        searchResult.setPrevStartRow(0);
        searchResult.setGeonames(expectedGeoNames());

        return searchResult;
    }

    private List<GeoNames> expectedGeoNames() {
        List<GeoNames> geoNamesList = new ArrayList<>();
        return  geoNamesList;
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
