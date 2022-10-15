package com.example.congestionTaxCalculator.dto;

import com.example.congestionTaxCalculator.domain.VehicleType;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;


@Builder
public class GetRequestBodyDto {
    @Getter
    public VehicleType vehicleType;

    @Getter
    public List<LocalDateTime> dateTimes;


}

