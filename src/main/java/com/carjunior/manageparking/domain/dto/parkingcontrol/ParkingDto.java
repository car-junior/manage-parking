package com.carjunior.manageparking.domain.dto.parkingcontrol;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ParkingDto {
    private String name;
    private String address;
}

