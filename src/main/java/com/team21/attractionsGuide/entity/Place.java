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
 * A class representing a Place object used to represent the data from the nearbySearch API call and getDetails
 * Date: 2023/04/06
 */

//all properties not defined are ignored by Jackson
@JsonIgnoreProperties(ignoreUnknown = true)
public class Place {

    @JsonProperty
    private String name;

    @JsonProperty
    private List address_components = new ArrayList();

    @JsonProperty
    private String formatted_address;

    @JsonProperty
    private String business_status;

    @JsonProperty
    private String formatted_phone_number;

    @JsonProperty
    private List photos = new ArrayList();

    @JsonProperty
    private Integer rating;

    @JsonProperty
    private Integer price_level;

    @JsonProperty
    private List types = new ArrayList();

    //official google page url (application must link this page on screen due to the google API agreement)
    @JsonProperty
    private String url;

    //contains summary/textual overview of the place and a language code
    @JsonProperty
    private Object editorial_summary;

    @JsonProperty
    private String website;

    @JsonProperty
    private Object opening_hours = new Object();

    /**
     * Constructor with parameters
     * @return returns a Place object
     */

    public Place(String name, List address_components, String formatted_address, String business_status, String formatted_phone_number, List photos, Integer rating, Integer price_level, List types, String url, Object editorial_summary, String website, Object opening_hours) {
        this.name = name;
        this.address_components = address_components;
        this.formatted_address = formatted_address;
        this.business_status = business_status;
        this.formatted_phone_number = formatted_phone_number;
        this.photos = photos;
        this.rating = rating;
        this.price_level = price_level;
        this.types = types;
        this.url = url;
        this.editorial_summary = editorial_summary;
        this.website = website;
        this.opening_hours = opening_hours;
    }

    /**
     * a no-arg constructor as required by Jackson to serialise JSON data into a Place Object
     */
    public Place() {}

    /**
     * Setters and Getters for the Place Object
     * accessed by jackson with the no-arg constructor to set the properties of the Place Object
     */

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List getAddress_components() {
        return address_components;
    }

    public void setAddress_components(List address_components) {
        this.address_components = address_components;
    }

    public String getFormatted_address() {
        return formatted_address;
    }

    public void setFormatted_address(String formatted_address) {
        this.formatted_address = formatted_address;
    }

    public String getBusiness_status() {
        return business_status;
    }

    public void setBusiness_status(String business_status) {
        this.business_status = business_status;
    }

    public String getFormatted_phone_number() {
        return formatted_phone_number;
    }

    public void setFormatted_phone_number(String formatted_phone_number) {
        this.formatted_phone_number = formatted_phone_number;
    }

    public List getPhotos() {
        return photos;
    }

    public void setPhotos(List photos) {
        this.photos = photos;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Integer getPrice_level() {
        return price_level;
    }

    public void setPrice_level(Integer price_level) {
        this.price_level = price_level;
    }

    public List getTypes() {
        return types;
    }

    public void setTypes(List types) {
        this.types = types;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Object getEditorial_summary() {
        return editorial_summary;
    }

    public void setEditorial_summary(Object editorial_summary) {
        this.editorial_summary = editorial_summary;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Object getOpening_hours() {
        return opening_hours;
    }

    public void setOpening_hours(Object opening_hours) {
        this.opening_hours = opening_hours;
    }

    /**
     * A function that deserializes a JSON string from the getDetails API into a POJO Place object
     * returns an ArrayList of POJO Place objects
     * @param response the JSON string of google API data we wish to format
     * @return an ArrayList of POJO Place objects
     * @throws JsonProcessingException
     */

    public static ArrayList<Place> formatPlacesResult(String response) throws JsonProcessingException {

        ArrayList<Place> placeArrayList = new ArrayList<Place>();

        try {

            //parse contents of the JSON string into a JSON object
            JsonObject jsonObject = JsonParser.parseString(response).getAsJsonObject();

            //get the results array from the JSON object
            JsonArray jsonArray = (JsonArray)jsonObject.get("results");

            //iterate the contents of the results array
            ObjectMapper objectMapper = new ObjectMapper();

            for (int i=0; i < jsonArray.size(); i++) {
                String placeJson = jsonArray.get(i).toString();

                //map a Place objects variables with each matching object in the results array
                Place place = objectMapper.readValue(placeJson, Place.class);
                placeArrayList.add(place);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        //return an array of place objects created from googles JSON data
        return placeArrayList;
    }

    /**
     * A function that deserializes a JSON string from the into a POJO Place object
     * returns a POJO Place object in the format specified
     * @param response the JSON string of google API data we wish to format
     * @return a POJO Place object
     * @throws JsonProcessingException
     */
    public static Place formatPlaceDetailsResult(String response) throws JsonProcessingException {

        //initialise an empty PlaceDetails object
        Place details = new Place();

        try {

            //parse contents of the JSON string into a JSON object
            JsonObject jsonObject = JsonParser.parseString(response).getAsJsonObject();

            //get the result object from the JSON object
            jsonObject = (JsonObject) jsonObject.get("result");

            //iterate the contents of the result object
            ObjectMapper objectMapper = new ObjectMapper();

            String placeDetailsJson = jsonObject.toString();

            //map the result object from the JSON string to the PlaceDetails Object
            details = objectMapper.readValue(placeDetailsJson, Place.class);

        } catch (Exception e) {
            e.printStackTrace();
        }
        //return the created PlaceDetails object
        return details;
    }

    /**
     * override of the default toString method
     * @return a string representation of the Place object
     */
    @Override
    public String toString() {
        return "Place{" +
                "name='" + name + '\'' +
                ", address_components=" + address_components +
                ", formatted_address='" + formatted_address + '\'' +
                ", business_status='" + business_status + '\'' +
                ", formatted_phone_number='" + formatted_phone_number + '\'' +
                ", photos=" + photos +
                ", rating=" + rating +
                ", price_level=" + price_level +
                ", types=" + types +
                ", url='" + url + '\'' +
                ", editorial_summary=" + editorial_summary +
                ", website='" + website + '\'' +
                ", opening_hours=" + opening_hours +
                '}';
    }
}
