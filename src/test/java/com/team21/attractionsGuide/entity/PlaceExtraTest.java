package com.team21.attractionsGuide.entity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;


public class PlaceExtraTest {

    private PlaceExtra pe;

    @BeforeEach
    public void setUp() {
        pe = new PlaceExtra("test_location_id_1", true, 2.5);
    }

    @Test
    public void setLocationId() {
        pe.setLocationId("test_location_id_2");
        assertEquals(pe.getLocationId(), "test_location_id_2");
    }

    @Test
    public void getLocationId() {
        assertEquals(pe.getLocationId(), "test_location_id_1");
    }

    @Test
    public void setAccessibility() {
        pe.setAccessibility(false);
        assertFalse(pe.getAccessibility());
    }

    @Test
    public void getAccessibility() {
        assertTrue(pe.getAccessibility());
    }

    @Test
    public void setAdmissionFee() {
        pe.setAdmissionFee(3.1);
        assertEquals(pe.getAdmissionFee(), 3.1);
    }

    @Test
    public void getAdmissionFee() {
        assertEquals(pe.getAdmissionFee(), 2.5);
    }

}
