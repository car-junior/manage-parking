package com.carjunior.manageparking.domain.dto.parkingcontrol.entrance;

import com.carjunior.manageparking.domain.dto.parkingcontrol.ParkingDto;
import com.carjunior.manageparking.domain.dto.parkingcontrol.VehicleDetailDto;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EntranceDetailDto {
    private long id;
    private ParkingDto parking;
    private VehicleDetailDto vehicle;
    private LocalDateTime entryDateTime;
}