package com.example.congestionTaxCalculator.handler;

import com.example.congestionTaxCalculator.handler.VehicleTypeNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.servlet.http.HttpServletRequest;


@ControllerAdvice
public class CongestionTaxCalculatorExceptionHandler {

    @ExceptionHandler(VehicleTypeNull.class)
    public ResponseEntity<String> handleException(VehicleTypeNull exception, HttpServletRequest request){
        return new ResponseEntity<>(exception.getMessage(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DateTimeNull.class)
    public ResponseEntity<String> handleException(DateTimeNull exception,HttpServletRequest request){
        return new ResponseEntity<>(exception.getMessage(),HttpStatus.BAD_REQUEST);
    }
}
