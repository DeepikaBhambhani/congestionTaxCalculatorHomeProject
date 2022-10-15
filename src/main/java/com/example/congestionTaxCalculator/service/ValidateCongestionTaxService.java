package com.example.congestionTaxCalculator.service;

import com.example.congestionTaxCalculator.domain.VehicleType;
import com.example.congestionTaxCalculator.dto.TimeIntervalFee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import java.util.List;
import java.util.Optional;

import static com.example.congestionTaxCalculator.config.Constants.*;
import static com.example.congestionTaxCalculator.config.InitialiseData.*;

@Service
public class ValidateCongestionTaxService {

    private static final Logger LOG = LoggerFactory.getLogger(CongestionTaxCalculatorService.class);

    public boolean isTollFreeVehicle(VehicleType vehicle) {
        return Optional.ofNullable(tollFreeVehicle.contains(vehicle)).orElse(false);
    }

    //this method will check weather requested date is a toll free date
    public boolean isTollFreeDate(LocalDate date) {
        LOG.info("Call to isTollFreeDate");

        if (date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY) {
            return true;
        }

        return holidayList.contains(date);
    }


    //this method will validate all the inputs received
    public boolean validateInputs(VehicleType vehicle, List<LocalDateTime> dates) {

        LOG.info(" Call to validateInputs");

        if (isTollFreeVehicle(vehicle)) {
            return false;
        }

        //this will throw an exception if list will contain multiple days
        if (dates.stream().map(LocalDateTime::toLocalDate)
                .anyMatch(date -> !date.equals(dates.get(0).toLocalDate()))) {
            LOG.error(MORE_THAN_ONE_DAY_MSG);
            throw new RuntimeException(MORE_THAN_ONE_DAY_MSG);
        }

        if (isTollFreeDate(dates.get(0).toLocalDate())) {
            return false;
        }

        LOG.info("vehicle, date and time  validated successfully");
        return true;
    }

    //this method will fetch toll amount from Yaml file with respective to time of travel
    public double getFee(LocalTime time) {
        return timeFeeList.stream()
                .filter(timeFee -> isMatched(timeFee, time))
                .findAny()
                .map(TimeIntervalFee::getFee)
                .orElse(0d);
    }

    private boolean isMatched(TimeIntervalFee timeFee, LocalTime time) {
        return withinStartTime(timeFee, time) && withinEndTime(timeFee, time);
    }

    private boolean withinStartTime(TimeIntervalFee timeFee, LocalTime time) {
        return (timeFee.getStartTime().equals(time) || timeFee.getStartTime().isBefore(time));
    }

    private boolean withinEndTime(TimeIntervalFee timeFee, LocalTime time) {
        return (timeFee.getEndTime().equals(time) || timeFee.getEndTime().isAfter(time));
    }

}
