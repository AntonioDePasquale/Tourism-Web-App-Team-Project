package com.team21.attractionsGuide.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.team21.attractionsGuide.entity.PlaceExtra;
import com.team21.attractionsGuide.repository.PlaceExtraRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PlaceExtraServiceTest {

    @Mock
    private PlaceExtraRepository placeExtraRepository;

    private PlaceExtraService placeExtraService;

    private static final String LOCATION_ID = "12345";

    @BeforeEach
    public void setUp() {
        placeExtraService = new PlaceExtraService(placeExtraRepository);
    }

    @Test
    public void testFindPlaceExtraByLocationId() {
        PlaceExtra placeExtra = new PlaceExtra("locationId", "name", "description");
        placeExtra.setLocationId(LOCATION_ID);

        // Mock the repository call to return the placeExtra object
        Mockito.when(placeExtraRepository.findBylocationId(LOCATION_ID)).thenReturn(placeExtra);

        // Call the service method and verify the returned result
        PlaceExtra result = placeExtraService.findPlaceExtraByLocationId(LOCATION_ID);
        assertThat(result).isNotNull();
        assertThat(result.getLocationId()).isEqualTo(LOCATION_ID);
    }
}
