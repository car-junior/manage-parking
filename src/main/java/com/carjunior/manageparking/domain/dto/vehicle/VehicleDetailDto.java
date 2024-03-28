package com.carjunior.manageparking.domain.dto.vehicle;

import com.carjunior.manageparking.domain.entity.enums.VehicleType;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehicleDetailDto {
    private long id;
    private String mark;
    private String model;
    private String color;
    private String plate;
    private VehicleType type;
}

