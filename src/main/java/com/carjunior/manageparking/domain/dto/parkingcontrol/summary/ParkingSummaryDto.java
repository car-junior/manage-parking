package com.carjunior.manageparking.domain.dto.parkingcontrol.summary;

import com.carjunior.manageparking.domain.dto.parking.ParkingDetailDto;
import com.carjunior.manageparking.domain.dto.parkingcontrol.ParkingDto;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ParkingSummaryDto extends ParkingDto {
    private long id;
}

