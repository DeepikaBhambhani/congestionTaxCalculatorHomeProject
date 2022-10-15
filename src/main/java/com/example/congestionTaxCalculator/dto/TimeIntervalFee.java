package com.example.congestionTaxCalculator.dto;

import lombok.Data;
import lombok.NonNull;
import lombok.experimental.Accessors;

import java.time.LocalTime;

@Data
@Accessors(chain = true)
@NonNull
public class TimeIntervalFee {
    private LocalTime startTime;
    private LocalTime endTime;
    private double fee;
}
