package com.team21.attractionsGuide.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.team21.attractionsGuide.entity.PlaceAutoComplete;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A class that provides services for the PlacesController
 * @since 2023/04/06
 */
public class GoogleMapService {

    //map to add formatted places into using the
    final Map<Integer, PlaceAutoComplete> placesMap = new HashMap<Integer, PlaceAutoComplete>();
    final String apiKey = "AIzaSyCOVP27tJRVNkn4D9BxLo6oap9nM3V-lVA";

    /**
     * Fetches nearby places using the Google Maps API based on the given latitude and longitude.
     *
     * @param latitude  - The latitude of the location
     * @param longitude - The longitude of the location
     * @return A JSON string containing nearby places
     */
    public String getNearbyPlacesApiString(double latitude, double longitude, String type) {
        RestTemplate restTemplate = new RestTemplate();
        // read the key from configuration in production environment

        String requestUrl = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location={location}&type={type}&radius={radius}&key={key}";

        HashMap<String, String> params = new HashMap<String, String>();
        params.put("location", latitude + "," + longitude);
        params.put("type", type);
        params.put("radius", "1500");
        params.put("key", apiKey);
        // JSON string, return directly or process it. Here just return.
        String resp = restTemplate.getForObject(requestUrl, String.class, params);
        return resp;
    }

    /**
     * Fetches autocomplete suggestions for place names using the Google Maps API based on
     * the given input and location.
     *
     * @param input     - The input string for place name suggestions
     * @param latitude  - The latitude of the location
     * @param longitude - The longitude of the location
     * @param radius    - The search radius around the given location
     * @return A JSON string containing autocomplete suggestions for place names
     */
    public String getAutocompletePlacesApiString(String input, double latitude, double longitude, Integer radius) {
        RestTemplate restTemplate = new RestTemplate();

        String requestUrl = "https://maps.googleapis.com/maps/api/place/autocomplete/json?input={input}&location={location}&origin={location}&radius={radius}&key={key}";

        HashMap<String, String> params = new HashMap<String, String>();
        params.put("input", input);
        params.put("location", latitude + "," + longitude);
        params.put("radius", Integer.toString(radius));
        params.put("key", apiKey);

        // JSON string, return directly or process it. Here just return.
        // TODO process JSON string into Array or List(make a separate function to format Json data into Places Object)

        String resp = restTemplate.getForObject(requestUrl, String.class, params);
        return resp;
    }

    /**
     * Uses a place ID from a place search to request more in-depth details such as opening/closing times etc.
     *
     * @param placeID     - The string placeID which is required for additional details
     * @return A JSON string containing autocomplete suggestions for place names
     */
    public String getPlaceDetailsApiString(String placeID) {
        RestTemplate restTemplate = new RestTemplate();

        // for the request URL there are more optional field parameters to filter returned details that have not been included
        // current version is the bare minimum required for the API to return JSON

        String requestUrl = "https://maps.googleapis.com/maps/api/place/details/json?place_id={placeID}&key={key}";
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("placeID", placeID);
        params.put("key", apiKey);
        String resp = restTemplate.getForObject(requestUrl, String.class, params);
        return resp;
    }

    /**
     * Function used to merge two Json strings, the first string is prioritised in case of key collisions.
     * Uses the jackson library for object mapper functionality
     *
     * @param tempArray results collection of all types
     * @return A JSON string containing the merged keys and values of the two strings
     */
    public String mergeJsonStrings(ArrayList<String> tempArray) throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> mergedMap = new HashMap<>();
        mergedMap.put("status", "OK");
        ArrayList<Object> mergedResult = new ArrayList<Object>();

        for (int i = 0; i < tempArray.size(); i ++) {
            Map<String, Object> tmpResp = mapper.readValue(tempArray.get(i), Map.class);
            ArrayList<Object> tmpRespResults = (ArrayList<Object>) tmpResp.get("results");
            mergedResult.addAll(tmpRespResults);
        }
        mergedMap.put("results", mergedResult);
        String result = mapper.writeValueAsString(mergedMap);

        return result;
    }
}
