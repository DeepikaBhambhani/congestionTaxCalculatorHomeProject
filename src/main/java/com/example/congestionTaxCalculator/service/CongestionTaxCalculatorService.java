package com.example.congestionTaxCalculator.service;

import com.example.congestionTaxCalculator.domain.VehicleType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.congestionTaxCalculator.config.Constants.MAX_FEE_FOR_ONE_DAY;
import static javax.management.timer.Timer.ONE_HOUR;


@Service
public class CongestionTaxCalculatorService {

    private static final Logger LOG = LoggerFactory.getLogger(CongestionTaxCalculatorService.class);

    private ValidateCongestionTaxService calService=new ValidateCongestionTaxService();

    //this method will valid all the inputs and calculate toll respectively
    public int getTollTax(VehicleType vehicle, List<LocalDateTime> dates) {
        LOG.info("getTollTax method started");
        //this method will validate all the input parameters
        if (!calService.validateInputs(vehicle, dates)) {
            return 0;
        }

        List<LocalTime> sortedTimeList = dates.stream()
                .map(LocalDateTime::toLocalTime)
                .sorted()
                .collect(Collectors.toList());

        if (sortedTimeList.isEmpty()) {
            return 0;
        }
        LOG.info("Calculating toll tax for the sorted list");
        LocalTime intervalStart = sortedTimeList.get(0);
        int totalFee = 0;
        for (LocalTime date : sortedTimeList) {
            double nextFee = calService.getFee(date);
            double tempFee = calService.getFee(intervalStart);
            long minutes = Duration.between(intervalStart, date).toMillis();

            if (minutes <= ONE_HOUR) {
                if (totalFee > 0) totalFee -= tempFee;
                if (nextFee >= tempFee) tempFee = nextFee;
                totalFee += tempFee;
            } else {
                intervalStart = date;
                totalFee += nextFee;
            }
        }
        if (totalFee > MAX_FEE_FOR_ONE_DAY) totalFee = MAX_FEE_FOR_ONE_DAY;

        LOG.debug("Total Fee is " + totalFee);
        LOG.info("Toll fee calculation ended");
        return totalFee;
    }

}
