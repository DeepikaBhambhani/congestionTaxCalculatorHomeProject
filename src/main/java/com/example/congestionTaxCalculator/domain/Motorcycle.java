package com.example.congestionTaxCalculator.domain;



public class Motorcycle implements Vehicle {
    @Override
    public VehicleType getVehicle() {
        return VehicleType.MOTORCYCLE;
    }
}
