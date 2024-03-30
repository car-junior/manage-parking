package com.carjunior.manageparking.domain.dto.parkingcontrol.summary;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class EntranceExitDto {
    private int quantity;
    private List<ParkingControlDto> parkingControls;
}

