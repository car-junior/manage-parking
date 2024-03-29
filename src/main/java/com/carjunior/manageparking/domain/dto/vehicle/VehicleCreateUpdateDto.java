package com.carjunior.manageparking.domain.dto.vehicle;

import com.carjunior.manageparking.domain.entity.enums.VehicleType;
import com.carjunior.manageparking.domain.utils.Utility;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import static com.carjunior.manageparking.domain.utils.Utility.onlyAlphaNumbers;

public record VehicleCreateUpdateDto(
        @NotNull @NotEmpty String mark,
        @NotNull @NotEmpty String model,
        @NotNull @NotEmpty String color,
        @NotNull @NotEmpty String plate,
        @NotNull VehicleType type
) {
    @Override
    public String mark() {
        return mark.trim();
    }

    @Override
    public String model() {
        return model.trim();
    }

    @Override
    public String color() {
        return color.trim();
    }

    @Override
    public String plate() {
        return onlyAlphaNumbers(plate).toUpperCase();
    }
}

