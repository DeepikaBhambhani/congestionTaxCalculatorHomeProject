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
    @NonNull
    public VehicleType vehicleType;

    @Getter
    @NonNull
    public List<LocalDateTime> dateTimes;


}

