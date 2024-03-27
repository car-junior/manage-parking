package com.carjunior.manageparking.domain.dto.parking;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record ParkingCreateDto(
        @NotNull @NotEmpty String name,
        @NotNull @NotEmpty String cnpj,
        @NotNull @NotEmpty String address,
        @NotNull @NotEmpty String phoneNumber,
        @NotNull @Min(10) Integer numberSpacesMotorcycles,
        @NotNull @Min(10) Integer numberSpacesCars
) {
}

