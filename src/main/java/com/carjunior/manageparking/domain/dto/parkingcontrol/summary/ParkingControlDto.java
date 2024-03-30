package com.carjunior.manageparking.domain.dto.parkingcontrol.summary;

import com.carjunior.manageparking.domain.dto.parkingcontrol.VehicleDetailDto;
import com.carjunior.manageparking.domain.entity.enums.ParkingControlStatus;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParkingControlDto {
    private long id;
    private VehicleDetailDto vehicle;
    private ParkingControlStatus status;
    private LocalDateTime entryDateTime;
    private LocalDateTime exitDateTime;
}
