package com.carjunior.manageparking.domain.spec.search;

import com.carjunior.manageparking.domain.entity.enums.VehicleType;
import lombok.Builder;

@Builder
public record VehicleSearch(String query, VehicleType type) {

}
