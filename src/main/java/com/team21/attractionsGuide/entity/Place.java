package com.team21.attractionsGuide.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Place {

    @JsonProperty
    private String description;
    @JsonProperty
    private Integer distance_meters;
    @JsonProperty
    private String place_id;
    @JsonProperty
    private List types = new ArrayList<>();

    //ignored properties of JSON objects
    @JsonIgnore
    public Array matched_substrings;
    public String reference;
    public Map structured_formatting;
    public Array terms;

    public Place(String description, Integer distanceMetres, String placeID, List types) {
        this.description = description;
        this.distance_meters = distanceMetres;
        this.place_id = placeID;
        this.types = types;
    }

    public Place() {}

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDistanceMetres() { return distance_meters;}

    public void setDistanceMetres(Integer distanceMetres) {
        this.distance_meters = distanceMetres;
    }

    public String getPlaceID() {
        return place_id;
    }

    public void setPlaceID(String placeID) {
        this.place_id = placeID;
    }

    public List getPlaceTypes() {
        return types;
    }

    public void setPlaceTypes(List placeTypes) {
        this.types = placeTypes;
    }

    public static void formatPlacesResult(String response) throws JsonProcessingException {
        // TODO process JSON string into places object
        // TODO this function is not currently working properly and needs more work

        try {

            //parse contents of the JSON string into a JSON object
            JsonObject jsonObject = JsonParser.parseString(response).getAsJsonObject();

            //get the predictions array from the JSON object
            JsonArray jsonArray = (JsonArray)jsonObject.get("predictions");

            //iterate the contents of the predictions array
            ObjectMapper objectMapper = new ObjectMapper();

            //TODO Finish iterating through the predictions array
            //TODO make sure that Place objects are correctly mapped from the array items

            for (int i=0; i < jsonArray.size(); i++) {
               String placeJson = jsonArray.get(i).toString();
               System.out.println(placeJson);

               Place place = objectMapper.readValue(placeJson, Place.class);
               System.out.println(place.getDescription());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
