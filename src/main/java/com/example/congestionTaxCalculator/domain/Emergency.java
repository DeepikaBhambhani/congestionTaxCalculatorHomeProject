package com.example.congestionTaxCalculator.domain;



public class Emergency implements Vehicle {
    @Override
    public VehicleType getVehicle() {
        return VehicleType.EMERGENCY;
    }
}
