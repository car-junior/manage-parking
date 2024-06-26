package com.carjunior.manageparking.domain.dto.parkingcontrol.entrance;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import static com.carjunior.manageparking.domain.utils.Utility.onlyAlphaNumbers;

public record EntranceCreateDto(
        @NotNull @Positive Long parkingId,
        @NotNull @NotEmpty String vehiclePlate
) {
    @Override
    public String vehiclePlate() {
        return onlyAlphaNumbers(vehiclePlate).toUpperCase();
    }
}
