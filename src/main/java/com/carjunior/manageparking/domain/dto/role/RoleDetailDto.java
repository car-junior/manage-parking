package com.carjunior.manageparking.domain.dto.role;

import com.carjunior.manageparking.domain.entity.enums.RoleType;
import com.carjunior.manageparking.domain.entity.enums.UserStatus;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleDetailDto {
    private long id;
    private String name;
    private RoleType type;
}

