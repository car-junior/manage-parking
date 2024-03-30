package com.carjunior.manageparking.domain.dto.parkingcontrol.summary;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SummaryDetailDto {
    private ParkingSummaryDto parking;
    private EntranceExitDto entrance;
    private EntranceExitDto exit;
}

