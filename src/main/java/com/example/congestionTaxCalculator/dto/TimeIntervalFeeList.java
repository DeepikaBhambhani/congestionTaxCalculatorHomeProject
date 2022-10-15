package com.example.congestionTaxCalculator.dto;

import lombok.Data;
import lombok.NonNull;

import java.util.List;

@Data
@NonNull
public class TimeIntervalFeeList {
    private List<TimeFeeObj> timeFeeList;
    @Data
    @NonNull
    public static class TimeFeeObj{
        private String start;
        private String end;
        private Double fee;
    }

}
