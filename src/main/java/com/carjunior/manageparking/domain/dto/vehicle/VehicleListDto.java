package com.carjunior.manageparking.domain.dto.vehicle;

import com.carjunior.manageparking.domain.entity.enums.VehicleType;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehicleListDto {
    private long id;
    private String model;
    private String plate;
    private VehicleType type;
}

