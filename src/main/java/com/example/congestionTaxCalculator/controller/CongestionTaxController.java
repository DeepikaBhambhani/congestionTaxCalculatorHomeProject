package com.example.congestionTaxCalculator.controller;

import com.example.congestionTaxCalculator.dto.GetRequestBodyDto;
import com.example.congestionTaxCalculator.service.CongestionTaxCalculatorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

@RestController
public class CongestionTaxController {

    private static final Logger LOG = LoggerFactory.getLogger(CongestionTaxController.class);
    @Autowired
    private CongestionTaxCalculatorService calculator;

    @GetMapping("/test")
    String test() {
        return "Hello, Welcome to Congestion Tax Calculator";
    }


    @PostMapping(value = "/tax", consumes = {"application/json"})
    int getTax(@RequestBody GetRequestBodyDto dto) {
        LOG.info("Received Http request with request body Vehicle Type: " + dto.getVehicleType() + " date and time:" + dto.getDateTimes());
        return calculator.getTollTax(dto.getVehicleType(), dto.getDateTimes());
    }


}
