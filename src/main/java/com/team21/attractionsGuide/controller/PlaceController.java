package com.team21.attractionsGuide.controller;

import com.fasterxml.jackson.annotation.JsonMerge;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.team21.attractionsGuide.service.GoogleMapService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
/**
 * PlaceController class handles HTTP requests related to the Google Places API.
 * Date: 2023/3/26
 */
@RestController
@CrossOrigin
@RequestMapping("/place")
public class PlaceController {

    /**
     * GoogleMapService is used for handling place-related business logic.
     */
    private GoogleMapService googleService = new GoogleMapService();

    /**
     * Creates a Place Nearby Search API request using frontend data with a service function for each type specified.
     * Each call creates a Json formatted String of nearby places which are then merged into a single Json String.
     * Merged Json string is returned to the client.
     *
     * @param latitude  - The latitude of the place
     * @param longitude - The longitude of the place
     * @return          - A merged Json string containing the results of the API calls for each type specified
     */
    @GetMapping(value = "/getNearbyAttractions", produces = "application/json;charset=UTF-8")
    @ResponseBody

    public String getNearbyPlaces(

            // Get parameters from the url
            // eg. /place/getNearbyAttractions?latitude=1&longitude=2, get '1' and '2'
            @RequestParam double latitude,
            @RequestParam double longitude

    ) throws JsonProcessingException {
        // TODO: Valid the parameters, springboot will check the type automatically.
        //  We should valid them logically.

        //Map of place types we wish to return from the nearby places API
        Map<String, String> placeTypes = new HashMap<>();
        placeTypes.put("type1", "museum");
        placeTypes.put("type2", "park");
        placeTypes.put("type3", "point_of_interest");
        placeTypes.put("type4", "library");

        //initialise an array of type String to store Json strings from the API
        ArrayList<String> tempStringArray = new ArrayList<String>();

        //for each map entry, use the value as a type param in the getNearbyPlacesApiString service function
        for (var entry : placeTypes.entrySet()) {

                String type = entry.getValue();
                String tempString = googleService.getNearbyPlacesApiString(latitude, longitude, type);

                //add each Json String to an array of Json Strings
                tempStringArray.add(tempString);
        }

        //takes two Strings from the tempStringArray and merges them into one
        //The 1st and 2nd indexes are merged, the merged Json is set to the place of the 2nd Json
        //the for loop continues and the now merged Json set to index 2 is to be merged with index 3
        for (int i = 0; i < tempStringArray.size() - 1; i++) {
            String firstJson = tempStringArray.get(i);
            String secondJson = tempStringArray.get(i + 1);

            tempStringArray.set(i + 1, googleService.mergeJsonStrings(firstJson, secondJson));
        }

        //String containing merged Json of all requests
        String mergedJson = tempStringArray.get(tempStringArray.size() - 1);

        // Respond with merged Json
        return mergedJson;
    }

    /**
     * Creates a Place AutoComplete API request using frontend data with a service function.
     * gets the result as a Json formatted String of places and sends it to the client.
     *
     * @param input     - The text string from which autocomplete results are suggested
     * @param latitude  - The latitude of the place
     * @param longitude - The longitude of the place
     * @param radius    - The radius from the coordinates which we want to retrieve places
     * @return          - A Json string containing the results of the API call
     */
    @GetMapping(value = "/getAutoComplete", produces = "application/json;charset=UTF-8")
    @ResponseBody

    public String getAutoCompletePlaces(

            // Get parameters from the url
            // eg. /place/getAutoCompletePlaces?latitude=1&longitude=2, get '1' and '2'
            @RequestParam String input,
            @RequestParam double latitude,
            @RequestParam double longitude,
            @RequestParam Integer radius
    ) {
        // TODO: Valid the parameters, springboot will check the type automatically.
        //  We should valid them logically.

        // handle by GoogleMapService class
        String respString = googleService.getAutocompletePlacesApiString(input, latitude, longitude, radius);
        // Respond with json
        return respString;
    }

    /**
     * Creates a Place Details API request using frontend data with a service function.
     * gets the result as a Json formatted String of place details and sends it to the client.
     *
     * @param placeID   - The placeID from Google Places required to get additional details
     * @return          - A Json string containing the results of the API call
     */
    @GetMapping(value = "/getDetails", produces = "application/json;charset=UTF-8")
    @ResponseBody

    public String getPlaceDetails(

            // Get parameters from the url
            // eg. /place/getDetails?placeID=123, get 123
            @RequestParam String placeID
    ) {
        // TODO: Valid the parameters, springboot will check the type automatically.
        //  We should valid them logically.

        // handle by GoogleMapService class
        String respString = googleService.getPlaceDetailsApiString(placeID);
        // Respond with json
        return respString;
    }
}
