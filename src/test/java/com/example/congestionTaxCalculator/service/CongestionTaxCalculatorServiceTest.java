package com.example.congestionTaxCalculator.service;


import com.example.congestionTaxCalculator.domain.Car;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static com.example.congestionTaxCalculator.config.Constants.MAX_FEE_FOR_ONE_DAY;
import static org.junit.jupiter.api.Assertions.assertEquals;



class CongestionTaxCalculatorServiceTest {

    private static CongestionTaxCalculatorService cal;
    private static LocalDate date;

    private static List<LocalDateTime> dates;

    @BeforeAll
    private static void initialiseDate(){
        cal=new CongestionTaxCalculatorService();
        date= LocalDate.of(LocalDate.now().getYear(),LocalDate.now().getMonth(),LocalDate.now().getDayOfMonth());
    }

    @Test
    @DisplayName("Toll test for vehicles travelling from midnight till morning")
    public void midnightMorningZeroFeeTimeTest() {
        dates = new ArrayList<>();
        // early morning time
        dates.add(LocalDateTime.of(date, LocalTime.of(0, 0)));
        dates.add(LocalDateTime.of(date, LocalTime.of(0, 59)));
        dates.add(LocalDateTime.of(date, LocalTime.of(1, 0)));
        dates.add(LocalDateTime.of(date, LocalTime.of(5, 59)));
        //midnight time
        dates.add(LocalDateTime.of(date, LocalTime.of(18, 30)));
        dates.add(LocalDateTime.of(date, LocalTime.of(19, 10)));
        dates.add(LocalDateTime.of(date, LocalTime.of(23, 40)));

        assertEquals(0, cal.getTollTax(new Car().getVehicle(), dates));
    }

    @Test
    @DisplayName("Toll test for vehicles traveling in the morning")
    public void morningRushHourTollTest() {
        dates = new ArrayList<>();
        dates.add(LocalDateTime.of(date, LocalTime.of(6, 0)));
        dates.add(LocalDateTime.of(date, LocalTime.of(6, 29)));
        assertEquals(8, cal.getTollTax(new Car().getVehicle(), dates));
    }

    @Test
    public void eveningRushHourTollTest() {
        dates = new ArrayList<>();
        dates.add(LocalDateTime.of(date, LocalTime.of(17, 0)));
        dates.add(LocalDateTime.of(date, LocalTime.of(17, 30)));
        dates.add(LocalDateTime.of(date, LocalTime.of(18, 0)));
        dates.add(LocalDateTime.of(date, LocalTime.of(18, 28)));


        assertEquals(21, cal.getTollTax(new Car().getVehicle(), dates));
    }

    @Test
    @DisplayName("Maximum toll calculation in one day")
    public void maxFeeCalcTest() {
         dates = new ArrayList<>();
        dates.add(LocalDateTime.of(date, LocalTime.of(6, 0)));
        dates.add(LocalDateTime.of(date, LocalTime.of(11, 35)));
        dates.add(LocalDateTime.of(date, LocalTime.of(12, 35)));
        dates.add(LocalDateTime.of(date, LocalTime.of(14, 35)));
        dates.add(LocalDateTime.of(date, LocalTime.of(15, 5)));
        dates.add(LocalDateTime.of(date, LocalTime.of(15, 30)));

        dates.add(LocalDateTime.of(date, LocalTime.of(16, 0)));
        dates.add(LocalDateTime.of(date, LocalTime.of(16, 30)));
        dates.add(LocalDateTime.of(date, LocalTime.of(17, 0)));
        dates.add(LocalDateTime.of(date, LocalTime.of(18, 0)));
        // total 118
        assertEquals(MAX_FEE_FOR_ONE_DAY, cal.getTollTax(new Car().getVehicle(), dates));
    }

    @Test
    @DisplayName("Calculate morning and evening toll")
    public void morningEveningTollFeeTest(){
        dates = new ArrayList<>();
        dates.add(LocalDateTime.of(date, LocalTime.of(11, 35)));
        dates.add(LocalDateTime.of(date, LocalTime.of(16, 0)));
        assertEquals(26, cal.getTollTax(new Car().getVehicle(), dates));
    }


}
