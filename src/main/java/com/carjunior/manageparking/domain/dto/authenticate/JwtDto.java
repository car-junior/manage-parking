package com.carjunior.manageparking.domain.dto.authenticate;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JwtDto {
    private String token;
}
