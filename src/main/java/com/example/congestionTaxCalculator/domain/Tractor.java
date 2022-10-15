package com.example.congestionTaxCalculator.domain;


public class Tractor implements Vehicle{

    @Override
    public VehicleType getVehicle() {
        return VehicleType.TRACTOR;
    }
}
