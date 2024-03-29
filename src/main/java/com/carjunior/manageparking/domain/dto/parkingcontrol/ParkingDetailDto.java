package com.carjunior.manageparking.domain.dto.parkingcontrol;

import com.carjunior.manageparking.domain.entity.enums.VehicleType;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParkingDetailDto {
    private String name;
    private String address;
}

