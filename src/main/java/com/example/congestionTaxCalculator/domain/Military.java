package com.example.congestionTaxCalculator.domain;




public class Military implements Vehicle {
    @Override
    public VehicleType getVehicle(){
        return VehicleType.MILITARY;
    }

}
