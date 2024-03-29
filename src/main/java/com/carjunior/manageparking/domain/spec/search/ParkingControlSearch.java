package com.carjunior.manageparking.domain.spec.search;

import com.carjunior.manageparking.domain.entity.enums.VehicleType;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ParkingControlSearch(String query, LocalDateTime start, LocalDateTime end, VehicleType type) {

}
