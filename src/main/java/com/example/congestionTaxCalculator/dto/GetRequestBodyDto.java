package com.example.congestionTaxCalculator.dto;

import com.example.congestionTaxCalculator.domain.VehicleType;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
public class GetRequestBodyDto {
    @Getter
    public VehicleType vehicleType;

    @Getter
    public List<LocalDateTime> dateTimes;


}

