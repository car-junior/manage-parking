package com.carjunior.manageparking.domain.dto.role.createupdate;

import com.carjunior.manageparking.domain.entity.enums.RoleType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoleCreateUpdateDto {
    @NotNull
    @NotEmpty
    private String name;
    @NotNull
    private RoleType type;
    @NotNull
    private ParkingDto parking;
}

