package com.team21.attractionsGuide.Places;

import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/place")
public class PlaceController {

    private GoogleMapService googleService = new GoogleMapService();

    @GetMapping(value = "/getNearbyAttractions", produces = "application/json;charset=UTF-8")
    @ResponseBody

    public String getNearbyPlaces(
            @RequestParam double latitude,
            @RequestParam double longitude
    ) {
        // Get parameters from the url
        // eg. /place/getNearbyAttractions?latitude=1&longitude=2, get '1' and '2'

        // TODO: Valid the parameters, springboot will check the type automatically.
        //  We should valid them logically.

        // handle by GoogleMapService class
        String respString = googleService.getNearbyPlacesApiString(latitude, longitude);
        // Respond with json
        return respString;
    }

    @GetMapping(value = "/getAutoComplete", produces = "application/json;charset=UTF-8")
    public String getAutoCompletePlaces(
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

    @GetMapping(value = "/getDetails", produces = "application/json;charset=UTF-8")
    public String getPlaceDetails(
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
