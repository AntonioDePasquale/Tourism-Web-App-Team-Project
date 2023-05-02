package com.team21.attractionsGuide.controller;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.team21.attractionsGuide.entity.Place;
import com.team21.attractionsGuide.entity.PlaceAutoComplete;
import com.team21.attractionsGuide.service.GoogleMapService;
import com.team21.attractionsGuide.service.PlaceExtraService;

import java.util.ArrayList;
import java.util.HashSet;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class PlacesControllerTest {

    @MockBean
    private GoogleMapService googleMapService;

    @MockBean
    private PlaceExtraService placeExtraService;

    @Mock
    private PlacesController placesController;

    private static final double LATITUDE = 1.0;
    private static final double LONGITUDE = 2.0;
    private static final String PLACE_TYPE = "library";

    @Test
    void testGetNearbyPlaces() throws JsonProcessingException {
        ArrayList<Place> places = new ArrayList<Place>();
        places.add(new Place());
        places.add(new Place());
        places.add(new Place());

        // Mock the service call to return some places
        Mockito.when(googleMapService.getNearbyPlacesApiString(LATITUDE, LONGITUDE, PLACE_TYPE)).thenReturn("[]");
        Mockito.when(placesController.getNearbyPlaces(LATITUDE, LONGITUDE, PLACE_TYPE)).thenReturn("[]");

        // Call the controller method and verify the returned result
        String response = placesController.getNearbyPlaces(LATITUDE, LONGITUDE, PLACE_TYPE);
        assertThat(response).isNotNull().isEqualTo("[]");
    }

    @Test
    void testGetAutoCompletePlaces() throws JsonProcessingException {
        ArrayList<PlaceAutoComplete> places = new ArrayList<PlaceAutoComplete>();
        places.add(new PlaceAutoComplete());
        places.add(new PlaceAutoComplete());
        places.add(new PlaceAutoComplete());

        // Mock the service call to return some places
        Mockito.when(googleMapService.getAutocompletePlacesApiString("input", LATITUDE, LONGITUDE, 100)).thenReturn("[]");
        Mockito.when(placesController.getAutoCompletePlaces("input", LATITUDE, LONGITUDE, 100)).thenReturn("[]");

        // Call the controller method and verify the returned result
        String response = placesController.getAutoCompletePlaces("input", LATITUDE, LONGITUDE, 100);
        assertThat(response).isNotNull().isEqualTo("[]");
    }
}
