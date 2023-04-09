package com.team21.attractionsGuide.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.team21.attractionsGuide.entity.PlaceAutoComplete;
import org.junit.jupiter.api.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class GoogleMapServiceTest {
    private GoogleMapService googleMapService;
    private final double latitude = 51.5074;
    private final double longitude = 0.1278;
    private final String type = "restaurant";
    private final String input = "London";
    private final Integer radius = 1500;

    @BeforeEach
    void setUp() {
        googleMapService = new GoogleMapService();
    }

    @AfterEach
    void tearDown() {
        googleMapService = null;
    }

    @Test
    @DisplayName("Test getNearbyPlacesApiString method")
    void testGetNearbyPlacesApiString() {
        String response = googleMapService.getNearbyPlacesApiString(latitude, longitude, type);
        assertNotNull(response);
    }

    @Test
    @DisplayName("Test getAutocompletePlacesApiString method")
    void testGetAutocompletePlacesApiString() {
        String response = googleMapService.getAutocompletePlacesApiString(input, latitude, longitude, radius);
        assertNotNull(response);
    }

    @Test
    @DisplayName("Test getPlaceDetailsApiString method")
    void testGetPlaceDetailsApiString() {
        String placeId = "ChIJdd4hrwug2EcRmSrV3Vo6llI"; //place id of "Big Ben"
        String response = googleMapService.getPlaceDetailsApiString(placeId);
        assertNotNull(response);
    }

    @Test
    @DisplayName("Test mergeJsonStrings method")
    void testMergeJsonStrings() throws JsonProcessingException {
        String jsonString1 = "{\"results\":[{\"name\":\"London Eye\"}],\"status\":\"OK\"}";
        String jsonString2 = "{\"results\":[{\"name\":\"Big Ben\"}],\"status\":\"OK\"}";
        ArrayList<String> tempArray = new ArrayList<>();
        tempArray.add(jsonString1);
        tempArray.add(jsonString2);
        String response = googleMapService.mergeJsonStrings(tempArray);
        assertNotNull(response);
    }
}

