package com.team21.attractionsGuide.service;

import com.team21.attractionsGuide.entity.Place;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;


public class GoogleMapService {

    //map to add formatted places into using the
    final Map<Integer, Place> placesMap = new HashMap<Integer, Place>();
    final String apiKey = "AIzaSyCOVP27tJRVNkn4D9BxLo6oap9nM3V-lVA";

    /**
     * Fetches nearby places using the Google Maps API based on the given latitude and longitude.
     *
     * @param latitude  - The latitude of the location
     * @param longitude - The longitude of the location
     * @return A JSON string containing nearby places
     */
    public String getNearbyPlaces(double latitude, double longitude) {
        RestTemplate restTemplate = new RestTemplate();
        // read the key from configuration in production environment

        String requestUrl = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location={location}&radius={radius}&key={key}";

        HashMap<String, String> params = new HashMap<String, String>();
        params.put("location", latitude + "," + longitude);
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
    public String getAutocompletePlacesList(String input, double latitude, double longitude, Integer radius) {
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
}
