package com.carjunior.manageparking.domain.dto.company;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDetailDto {
    private long id;
    private String name;
    private String address;
    private String phoneNumber;
    private Integer numberSpacesMotorcycles;
    private Integer numberSpacesCars;
}

