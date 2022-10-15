package com.example.congestionTaxCalculator.service;


import com.example.congestionTaxCalculator.domain.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class CongestionTaxCalculatorForFreeDayTests {


    public static CongestionTaxCalculatorService cal;
    private static LocalDate date;

    private static List<LocalDateTime> dates;

    @BeforeAll
    private static void initDate() {
        cal = new CongestionTaxCalculatorService();

    }

    @Test
    @DisplayName("Calculating Toll on weekends")
    public void weekendTollTest() {
        dates = new ArrayList<>();
        date = LocalDate.of(2022, 7, 31);
        dates.add(LocalDateTime.of(date, LocalTime.of(12, 52)));
        dates.add(LocalDateTime.of(date, LocalTime.of(13, 52)));
        dates.add(LocalDateTime.of(date, LocalTime.of(14, 52)));
        assertEquals(0, cal.getTollTax(new Car().getVehicle(), dates));
    }

    @Test
    @DisplayName("Test for toll free vehicles")
    public void tollFreeVehicles() {

        dates = new ArrayList<>();
        date = LocalDate.of(LocalDateTime.now().getYear(), LocalDateTime.now().getMonth(), LocalDateTime.now().getDayOfMonth());
        dates.add(LocalDateTime.of(date, LocalTime.of(12, 52)));
        dates.add(LocalDateTime.of(date, LocalTime.of(13, 52)));
        dates.add(LocalDateTime.of(date, LocalTime.of(14, 52)));
        assertEquals(0, cal.getTollTax(new Diplomat().getVehicle(), dates));
        assertEquals(0, cal.getTollTax(new Foreign().getVehicle(), dates));
        assertEquals(0, cal.getTollTax(new Military().getVehicle(), dates));
        assertEquals(0, cal.getTollTax(new Motorcycle().getVehicle(), dates));
        assertEquals(0, cal.getTollTax(new Tractor().getVehicle(), dates));
        assertEquals(0, cal.getTollTax(new Emergency().getVehicle(), dates));


    }

    @Test
    @DisplayName("Toll calculation on toll free days")
    public void freeDateTollTest() {
        int year = LocalDate.now().getYear();
        date = LocalDate.of(2022, 01, 01);
        dates = new ArrayList<>();
        dates.add(LocalDateTime.of(date, LocalTime.of(12, 52)));
        dates.add(LocalDateTime.of(date, LocalTime.of(15, 52)));
        assertEquals(0, cal.getTollTax(new Car().getVehicle(), dates));

    }

}
