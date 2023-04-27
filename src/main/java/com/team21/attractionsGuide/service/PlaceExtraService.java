package com.team21.attractionsGuide.service;

import com.team21.attractionsGuide.repository.PlaceExtraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.team21.attractionsGuide.entity.PlaceExtra;

/**
 * Service class responsible for managing PlaceExtra objects.
 */
@Service
public class PlaceExtraService {
    private final PlaceExtraRepository placeExtraRepository;

    /**
     * initialize repository
     * @param placeExtraRepository
     */
    @Autowired
    public PlaceExtraService(PlaceExtraRepository placeExtraRepository) {
        this.placeExtraRepository = placeExtraRepository;
    }

    public PlaceExtra findPlaceExtraByLocationId(String locationId) {
        PlaceExtra pe = placeExtraRepository.findBylocationId(locationId);
        return pe;
    }

}
