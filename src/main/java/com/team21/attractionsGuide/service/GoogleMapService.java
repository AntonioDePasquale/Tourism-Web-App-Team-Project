package com.team21.attractionsGuide.service;

import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import com.google.gson.Gson;


public class GoogleMapService {

    public String getNearbyPlaces(double latitude, double longtitude) {
        RestTemplate restTemplate = new RestTemplate();
        // read the key from configuration in production environment
        String apiKey = "AIzaSyCOVP27tJRVNkn4D9BxLo6oap9nM3V-lVA";

        String requestUrl = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location={location}&radius={radius}&key={key}";

        HashMap<String, String> params = new HashMap<String, String>();
        params.put("location", latitude + "," + longtitude);
        params.put("radius", "1500");
        params.put("key", apiKey);
        // JSON string, return directly or process it. Here just return.
        String resp = restTemplate.getForObject(requestUrl, String.class, params);
        return resp;

    }

}
