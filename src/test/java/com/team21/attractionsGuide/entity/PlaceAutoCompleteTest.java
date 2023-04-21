package com.team21.attractionsGuide.entity;

import com.fasterxml.jackson.core.JsonProcessingException;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PlaceAutoCompleteTest {

    @Test
    public void testPlaceAutoCompleteConstructorAndGetters() {
        String description = "Test Place";
        Integer distanceMetres = 1000;
        String placeID = "12345";
        List<String> placeTypes = new ArrayList<>();
        placeTypes.add("type1");
        Object structuredFormatting = null;

        PlaceAutoComplete place = new PlaceAutoComplete(description, distanceMetres, placeID, placeTypes, structuredFormatting);

        assertEquals(description, place.getDescription());
        assertEquals(distanceMetres, place.getDistanceMetres());
        assertEquals(placeID, place.getPlaceID());
        assertEquals(placeTypes, place.getPlaceTypes());
        assertEquals(structuredFormatting, place.getStructured_formatting());
    }

    @Test
    public void testPlaceAutoCompleteJsonParsing() throws JsonProcessingException {
        String response = "{\n" +
                "   \"predictions\" : [\n" +
                "      {\n" +
                "         \"description\" : \"Test Place\",\n" +
                "         \"distance_meters\" : 1000,\n" +
                "         \"place_id\" : \"12345\",\n" +
                "         \"types\" : [\n" +
                "            \"type1\"\n" +
                "         ],\n" +
                "         \"structured_formatting\" : null\n" +
                "      }\n" +
                "   ]\n" +
                "}";
        ArrayList<PlaceAutoComplete> places = PlaceAutoComplete.formatPlacesResult(response);
        assertNotNull(places);
        assertEquals(1, places.size());
        PlaceAutoComplete place = places.get(0);
        assertEquals("Test Place", place.getDescription());
        assertEquals(Integer.valueOf(1000), place.getDistanceMetres());
        assertEquals("12345", place.getPlaceID());
        List<String> placeTypes = new ArrayList<>();
        placeTypes.add("type1");
        assertEquals(placeTypes, place.getPlaceTypes());
        assertEquals(null, place.getStructured_formatting());
    }
}

