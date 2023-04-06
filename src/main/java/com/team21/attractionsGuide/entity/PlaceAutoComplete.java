package com.team21.attractionsGuide.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

/**
 * A class representing a PlaceAutoComplete object used to represent the data from the placeAutoComplete API call
 * @since 2023/04/06
 */

//all properties not defined are ignored by Jackson
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlaceAutoComplete {

    @JsonProperty
    private Integer distance_meters;
    @JsonProperty
    private String description;
    @JsonProperty
    private String place_id;
    @JsonProperty
    private List types = new ArrayList<>();

    /**
     * Constructor with parameters
     * @return returns a PlaceAutoComplete object
     */
    public PlaceAutoComplete(String description, Integer distanceMetres, String placeID, List types) {
        this.description = description;
        this.distance_meters = distanceMetres;
        this.place_id = placeID;
        this.types = types;
    }

    /**
     * a no-arg constructor as required by Jackson to serialise JSON data into a PlaceAutoComplete Object
     */
    public PlaceAutoComplete() {};

    /**
     * Setters and Getters for the PlaceAutoComplete Object
     * accessed by jackson with the no-arg constructor to set the properties of the PlaceAutoComplete Object
     */
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

    /**
     * A function that deserializes a JSON string from the getDetails API into a POJO PlaceAutoComplete object
     * returns an ArrayList of POJO PlaceAutoComplete objects
     * @param response the JSON string of google API data we wish to format
     * @return an ArrayList of POJO PlaceAutoComplete objects
     * @throws JsonProcessingException
     */
    public static ArrayList<PlaceAutoComplete> formatPlacesResult(String response) throws JsonProcessingException {

        ArrayList<PlaceAutoComplete> placeArrayList = new ArrayList<PlaceAutoComplete>();

        try {

            //parse contents of the JSON string into a JSON object
            JsonObject jsonObject = JsonParser.parseString(response).getAsJsonObject();

            //get the predictions array from the JSON object
            JsonArray jsonArray = (JsonArray)jsonObject.get("predictions");

            //iterate the contents of the predictions array
            ObjectMapper objectMapper = new ObjectMapper();

            for (int i=0; i < jsonArray.size(); i++) {
               String placeJson = jsonArray.get(i).toString();

               PlaceAutoComplete place = objectMapper.readValue(placeJson, PlaceAutoComplete.class);
               placeArrayList.add(place);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return placeArrayList;
    }

    /**
     * override of the default toString method
     * @return a string representation of the PlaceAutoComplete object
     */
    @Override
    public String toString() {
        return "PlaceAutoComplete{" +
                "description='" + description + '\'' +
                ", distance_meters=" + distance_meters +
                ", place_id='" + place_id + '\'' +
                ", types=" + types +
                '}';
    }
}
