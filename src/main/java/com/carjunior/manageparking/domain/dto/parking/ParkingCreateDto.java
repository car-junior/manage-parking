package com.carjunior.manageparking.domain.dto.parking;

import com.carjunior.manageparking.domain.validators.phone.PhoneBR;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.hibernate.validator.constraints.br.CNPJ;

import static com.carjunior.manageparking.domain.utils.Utility.onlyNumbers;

public record ParkingCreateDto(
        @NotNull @NotEmpty String name,
        @NotNull @NotEmpty @CNPJ String cnpj,
        @NotNull @NotEmpty String address,
        @NotNull @NotEmpty @PhoneBR String phoneNumber,
        @NotNull @Positive @Min(10) Integer numberSpacesMotorcycles,
        @NotNull @Positive @Min(10) Integer numberSpacesCars
) {
    public ParkingCreateDto {
        name = name.trim();
        cnpj = onlyNumbers(cnpj);
        address = address.trim();
        phoneNumber = onlyNumbers(phoneNumber);
    }
}

