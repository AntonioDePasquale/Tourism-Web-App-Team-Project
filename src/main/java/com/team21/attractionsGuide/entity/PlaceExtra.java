package com.team21.attractionsGuide.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * This class representing a PlaceExtra object
 * which contains extra information about a place
 * like accessibility, admission fee, etc.
 * @author Jiayu Ran
 * Date:  26/04/2023
 */
@Entity
@Table(name = "place_extra")
public class PlaceExtra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Location Id can't be blank")
    @NotNull
    private String locationId;

    private boolean accessibility;

    private double admissionFee;

    public PlaceExtra() {
        this.admissionFee = 0;
        this.accessibility = false;
    }

    public PlaceExtra(String locationId, boolean accessibility, double admissionFee) {
        this.locationId = locationId;
        this.accessibility = accessibility;
        this.admissionFee = admissionFee;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public void setAccessibility(boolean accessibility) {
        this.accessibility = accessibility;
    }

    public boolean getAccessibility() {
        return accessibility;
    }

    public void setAdmissionFee(double admissionFee) {
        this.admissionFee = admissionFee;
    }

    public double getAdmissionFee() {
        return admissionFee;
    }
}
