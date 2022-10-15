package com.example.congestionTaxCalculator.domain;



public class Foreign implements Vehicle{
    @Override
    public VehicleType getVehicle() {
        return VehicleType.FOREIGN;
    }
}
