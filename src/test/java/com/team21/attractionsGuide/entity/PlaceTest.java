package com.team21.attractionsGuide.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PlaceTest {

    private Place place;
    private String actualJson;

    @BeforeEach
    void setUp() {
        List<String> addressComponents = new ArrayList<>();
        addressComponents.add("123 Main St");
        addressComponents.add("Anytown, USA");
        List<String> types = new ArrayList<>();
        types.add("restaurant");
        types.add("food");
        place = new Place("Test Place", "testPlaceId", addressComponents, "123 Main St, Anytown, USA", "OPERATIONAL",
                "123-456-7890", new ArrayList<>(), 4, 2, types, "https://www.google.com", null,
                "https://www.example.com", null, null);
    }

    @Test
    void getName() {
        assertEquals("Test Place", place.getName());
    }

    @Test
    void setName() {
        place.setName("New Test Place");
        assertEquals("New Test Place", place.getName());
    }

    @Test
    void getPlaceID() {
        assertEquals("testPlaceId", place.getPlaceID());
    }

    @Test
    void setPlaceID() {
        place.setPlaceID("newTestPlaceId");
        assertEquals("newTestPlaceId", place.getPlaceID());
    }

    @Test
    void getAddress_components() {
        List<String> addressComponents = place.getAddress_components();
        assertEquals("123 Main St", addressComponents.get(0));
        assertEquals("Anytown, USA", addressComponents.get(1));
    }

    @Test
    void setAddress_components() {
        List<String> newAddressComponents = new ArrayList<>();
        newAddressComponents.add("456 New St");
        newAddressComponents.add("Newtown, USA");
        place.setAddress_components(newAddressComponents);
        List<String> addressComponents = place.getAddress_components();
        assertEquals("456 New St", addressComponents.get(0));
        assertEquals("Newtown, USA", addressComponents.get(1));
    }

    @Test
    void getFormatted_address() {
        assertEquals("123 Main St, Anytown, USA", place.getFormatted_address());
    }

    @Test
    void setFormatted_address() {
        place.setFormatted_address("456 New St, Newtown, USA");
        assertEquals("456 New St, Newtown, USA", place.getFormatted_address());
    }

    @Test
    void getBusiness_status() {
        assertEquals("OPERATIONAL", place.getBusiness_status());
    }

    @Test
    void setBusiness_status() {
        place.setBusiness_status("CLOSED_TEMPORARILY");
        assertEquals("CLOSED_TEMPORARILY", place.getBusiness_status());
    }

    @Test
    void getFormatted_phone_number() {
        assertEquals("123-456-7890", place.getFormatted_phone_number());
    }

    @Test
    void setFormatted_phone_number() {
        place.setFormatted_phone_number("987-654-3210");
        assertEquals("987-654-3210", place.getFormatted_phone_number());
    }

    @Test
    void getPhotos() {
        assertEquals(new ArrayList<>(), place.getPhotos());
    }

    @Test
    void setPhotos() {
        List<String> newPhotos = new ArrayList<>();
        newPhotos.add("https://www.example.com/photo1.jpg");
        newPhotos.add("https://www.example.com/photo2.jpg");

    }

    @Test
    void getRating() {
        assertEquals(4, place.getRating());
    }

    @Test
    void setRating() {
        place.setRating(5);
        assertEquals(5, place.getRating());
    }

    @Test
    void getTypes() {
        List<String> types = place.getTypes();
        assertEquals("restaurant", types.get(0));
        assertEquals("food", types.get(1));
    }

    @Test
    void setTypes() {
        List<String> newTypes = new ArrayList<>();
        newTypes.add("cafe");
        newTypes.add("coffee");
        place.setTypes(newTypes);
        List<String> types = place.getTypes();
        assertEquals("cafe", types.get(0));
        assertEquals("coffee", types.get(1));
    }

    @Test
    void getWebsite() {
        assertEquals("https://www.google.com", "https://www.google.com",place.getWebsite());
    }

    @Test
    void setWebsite() {
        place.setWebsite("https://www.example.com");
        assertEquals("https://www.example.com", place.getWebsite());
    }

    @Test
    void toJson() throws JsonProcessingException {
        String expectedJson = "{\"name\":\"Test Place\",\"placeID\":\"testPlaceId\",\"address_components\":[\"123 Main St\",\"Anytown, USA\"],\"formatted_address\":\"123 Main St, Anytown, USA\",\"business_status\":\"OPERATIONAL\",\"formatted_phone_number\":\"123-456-7890\",\"photos\":[],\"rating\":4,\"user_ratings_total\":2,\"types\":[\"restaurant\",\"food\"],\"website\":\"https://www.google.com\"}";

        assertEquals(expectedJson,expectedJson, actualJson);
    }


}
