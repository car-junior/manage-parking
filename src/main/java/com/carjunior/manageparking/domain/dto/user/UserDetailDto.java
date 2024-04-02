package com.carjunior.manageparking.domain.dto.user;

import com.carjunior.manageparking.domain.entity.enums.UserStatus;
import com.carjunior.manageparking.domain.entity.enums.VehicleType;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailDto {
    private long id;
    private String name;
    private String surname;
    private String email;
    private UserStatus status;
}

