package com.example.congestionTaxCalculator.controller;

import com.example.congestionTaxCalculator.domain.Car;
import com.example.congestionTaxCalculator.domain.VehicleType;
import com.example.congestionTaxCalculator.dto.GetRequestBodyDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(value = ControllerNullTest.class)
public class ControllerNullTest {
    @MockBean
    private static CongestionTaxController taxController;
    private static LocalDate date;
    private static ObjectMapper mapper;
    private static List<LocalDateTime> dates;
    @Autowired
    private MockMvc mockMvc;

    @BeforeAll
    public static void init() {
        mapper = new ObjectMapper();
        dates=new ArrayList<>();
    }

    @Test
    @DisplayName("passign null value for vehicle")
    public void vehicleNullTest() throws Exception {
        dates = getDateTime();
        GetRequestBodyDto dto = getDto(null,dates);
        mapper.registerModule(new JavaTimeModule());
        String taxJson = mapper.writeValueAsString(dto);

        MockHttpServletRequestBuilder reqBuilder = MockMvcRequestBuilders.post("/tax")
                .contentType(MediaType.APPLICATION_JSON).content(taxJson);

        ResultActions action = mockMvc.perform(reqBuilder).andDo(print());
        MvcResult mvcResult = action.andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        int status = response.getStatus();
        assertEquals(200, status);

    }

    @Test
    @DisplayName("passing null value for date time")
    public void dateNullTest() throws Exception {

        GetRequestBodyDto dto = getDto(new Car().getVehicle(),null);
        mapper.registerModule(new JavaTimeModule());
        String taxJson = mapper.writeValueAsString(dto);

        MockHttpServletRequestBuilder reqBuilder = MockMvcRequestBuilders.post("/tax")
                .contentType(MediaType.APPLICATION_JSON).content(taxJson);

        ResultActions action = mockMvc.perform(reqBuilder).andDo(print());
        MvcResult mvcResult = action.andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        int status = response.getStatus();
        assertEquals(200, status);

    }


    public GetRequestBodyDto getDto(VehicleType vehicle,List<LocalDateTime> dates) {
        return GetRequestBodyDto.builder()
                .vehicleType(vehicle)
                .dateTimes(dates)
                .build();
    }

    public List<LocalDateTime> getDateTime() {
        date = LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), LocalDate.now().getDayOfMonth());
        dates.add(LocalDateTime.of(date, LocalTime.of(17, 0)));
        dates.add(LocalDateTime.of(date, LocalTime.of(17, 30)));
        dates.add(LocalDateTime.of(date, LocalTime.of(18, 0)));
        dates.add(LocalDateTime.of(date, LocalTime.of(18, 28)));
        return dates;
    }
}
