package com.example.congestionTaxCalculator.domain;



public class Diplomat implements Vehicle{
    @Override
    public VehicleType getVehicle() {
        return VehicleType.DIPLOMAT;
    }
}
