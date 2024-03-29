package com.carjunior.manageparking.domain.dto.parkingcontrol;

import com.carjunior.manageparking.domain.entity.enums.VehicleType;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehicleEntranceDetailDto {
    private long id;
    private ParkingDetailDto parking;
    private VehicleDetailDto vehicle;
    @Setter(AccessLevel.NONE)
    private LocalDateTime entrance;
    public void setEntryDateTime(LocalDateTime entryDateTime) {
        this.entrance = entryDateTime;
    }
}

