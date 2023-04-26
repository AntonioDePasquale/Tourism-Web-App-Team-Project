package com.team21.attractionsGuide.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.team21.attractionsGuide.entity.PlaceAutoComplete;
import com.team21.attractionsGuide.entity.Place;
import com.team21.attractionsGuide.entity.PlaceExtra;
import com.team21.attractionsGuide.service.GoogleMapService;
import com.team21.attractionsGuide.service.PlaceExtraService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * PlaceController class handles HTTP requests related to the Google Places API.
 * Date: 2023/3/26
 */
@RestController
@RequestMapping("/place")
public class PlacesController {

    /**
     * GoogleMapService is used for handling place-related business logic.
     */
    private final GoogleMapService googleService = new GoogleMapService();
    private final PlaceExtraService placeExtraService;

    /**
     * Constructor, injecting the implementation of PlaceExtraService as a dependency.
     * @param pes service instance
     */
    public PlacesController(PlaceExtraService pes) {
        this.placeExtraService = pes;
    }

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
            // eg. /place/getNearbyAttractions?latitude=1&longitude=2&placeType=library|museum
            @RequestParam double latitude,
            @RequestParam double longitude,
            @RequestParam(required = false) String placeType
    ) throws JsonProcessingException {
        // Map of place types we wish to return from the nearby places API
        // Get types from front-end
        Map<String, String> placeTypes = new HashMap<>();
        // if front-end did not give types, default value 'library'
        if (placeType != null) {
            String[] types = placeType.split(",");
            for (int i = 0; i < types.length; i ++) {
                placeTypes.put("type" + i, types[i]);
            }
        } else {
            placeTypes.put("type1", "library");
        }
        //initialise an array of type String to store Json strings from the API
        ArrayList<String> tempStringArray = new ArrayList<String>();
        //for each map entry, use the value as a type param in the getNearbyPlacesApiString service function
        for (var entry : placeTypes.entrySet()) {
                String type = entry.getValue();
                String tempString = googleService.getNearbyPlacesApiString(latitude, longitude, type);
                //add each Json String to an array of Json Strings
                tempStringArray.add(tempString);
        }
        //String containing merged Json of all requests
        String mergedJson = googleService.mergeJsonStrings(tempStringArray);
        //format the response string into a place details Object
        ArrayList<Place> placeResponseArray = Place.formatPlacesResult(mergedJson);

        // remove duplicate items by place_id
        placeResponseArray = removeDuplicatedPlace(placeResponseArray);
        // reassign place's type (history building)
        for (int i = 0; i < placeResponseArray.size(); i ++) {
            Place p = placeResponseArray.get(i);
            p = reassignPlaceType(p);
            placeResponseArray.set(i, p);
        }
        // Respond with json
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(placeResponseArray);
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

    ) throws JsonProcessingException {

        // handle by GoogleMapService class
        String respString = googleService.getAutocompletePlacesApiString(input, latitude, longitude, radius);

        //format the response string into a place details Object
        ArrayList<PlaceAutoComplete> placeResponseArray = PlaceAutoComplete.formatPlacesResult(respString);

        // Respond with json
        ObjectMapper mapper = new ObjectMapper();

        return mapper.writeValueAsString(placeResponseArray);
    }

    /**
     * Creates a Place Details API request using frontend data with a service function.
     * gets the result as a Json formatted String of place details and formats it to a PlaceDetails Object
     * then sends it to the client.
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

    ) throws JsonProcessingException {

        // handle by GoogleMapService class
        String respString = googleService.getPlaceDetailsApiString(placeID);

        //format into PlaceDetails object
        Place place = Place.formatPlaceDetailsResult(respString);

        // change the type of place
        place = reassignPlaceType(place);

        // Respond with json
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(place);
    }

    /**
     * give the other information about the place that google map does not have.
     * @param placeID place_id from google map
     * @return PlaceExtra json
     * @throws JsonProcessingException json decode error
     */
    @GetMapping(value = "/getExtraInfo", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getPlaceExtraInfo(@RequestParam String placeID) throws JsonProcessingException {
        PlaceExtra pe = placeExtraService.findPlaceExtraByLocationId(placeID);
        System.out.println(pe);
        if (pe != null) {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(pe);
        }
        return "{}";
    }

    /**
     * Remove duplicated places by place_id
     * @param places      - ArrayList. all places to be returned.
     * @return            - ArrayList
     */
    private ArrayList<Place> removeDuplicatedPlace(ArrayList<Place> places) {
        ArrayList<Place> ret = new ArrayList<Place>();
        HashSet<String> placeIDs = new HashSet<String>();
        for (int i = 0; i < places.size(); i ++) {
            Place p = places.get(i);
            if (!placeIDs.contains(p.getPlaceID())) {
                ret.add(p);
                placeIDs.add(p.getPlaceID());
            }
        }
        return ret;
    }

    /**
     * If a place name contains "church, castle, ancient...", we assume that it is a history building.
     * @param p         - A place instance.
     * @return          - A place instance with correct type
     */
    private Place reassignPlaceType(Place p) {
        ArrayList<String> targets = new ArrayList<>();
        // some iconic history building name
        targets.add("abbey");
        targets.add("castle");
        targets.add("church");
        targets.add("cathedral");
        targets.add("palace");
        targets.add("chapel");
        String name = p.getName().toLowerCase();
        for (int i = 0; i < targets.size(); i ++) {
            if (name.contains(targets.get(i))) {
                // yes we think it is a history building
                ArrayList<String> originTypes = (ArrayList<String>) p.getTypes();
                originTypes.add("history_building");
                p.setTypes(originTypes);
                break;
            }
        }
        return p;
    }

    /**
     * Find other information store in database, like accessibility information
     * @param placeID
     * @return
     */
    private PlaceExtra getExtraInfo(String placeID) {
        PlaceExtra p = placeExtraService.findPlaceExtraByLocationId(placeID);
        return p;
    }

}
