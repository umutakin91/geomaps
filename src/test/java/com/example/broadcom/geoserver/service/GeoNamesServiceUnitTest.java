package com.example.broadcom.geoserver.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.example.broadcom.geonames.ws.model.GeoNames;
import com.example.broadcom.geonames.ws.model.SearchResult;
import com.example.broadcom.geoserver.dto.SearchQueryDto;
import com.example.broadcom.geoserver.ws.rest.RestClient;

/**
 * Geonames service test for search and get possible languages.
 *
 * @author Umut AKIN
 */
@RunWith(SpringRunner.class)
public class GeoNamesServiceUnitTest {

    private String geoNamesServiceUrl;

    @MockBean(name = "geonamesRestClient")
    @Qualifier("geonamesRestClient")
    private RestClient restClient;

    @InjectMocks
    private GeoNamesServiceImpl geoNamesService;

    @Before
    public void setup() {
          geoNamesServiceUrl = "/searchJSON";
          ReflectionTestUtils.setField(geoNamesService, "geoNamesServiceUrl", geoNamesServiceUrl);
          ReflectionTestUtils.setField(geoNamesService, "username", "umut");
          ReflectionTestUtils.setField(geoNamesService, "style", "small");
          ReflectionTestUtils.setField(geoNamesService, "maxRows", 2);
          ReflectionTestUtils.setField(geoNamesService, "formatted", true);
          ReflectionTestUtils.setField(geoNamesService, "restClient", restClient);
          ReflectionTestUtils.setField(geoNamesService, "languageList", getLanguageList());
    }

    @Test
    public void whenSearch_thenReturnSearchResultList() {
        ResponseEntity<? extends SearchResult>  responseEntity =
                new ResponseEntity<> (expectedSearchResult(), HttpStatus.OK);
        doReturn(responseEntity)
                .when(restClient)
                .get("/searchJSON?q=Turkish&lang=en&username=umut" +
                                "&style=small&maxRows=2&formatted=true&startRow=2",
                        SearchResult.class);

        SearchResult searchResult = geoNamesService.search(prepareSearchQueryDto());

        assertThat(searchResult).isEqualToComparingFieldByFieldRecursively(expectedSearchResult());
    }

    @Test
    public void whenGetLanguages_thenLanguageList() {

        List<String> languageList = getLanguageList();

        List<String> resultList = geoNamesService.getLanguages();

        assertThat(resultList).isEqualTo(languageList);

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

    private List<String> getLanguageList() {
        return Arrays.asList("en","es");
    }
}
