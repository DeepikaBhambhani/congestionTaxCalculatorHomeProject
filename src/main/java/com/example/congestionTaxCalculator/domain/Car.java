package com.example.congestionTaxCalculator.domain;



public class Car implements Vehicle {
    @Override
    public VehicleType getVehicle() {
        return VehicleType.CAR;
    }
}
