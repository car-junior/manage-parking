package com.carjunior.manageparking.domain.dto.company;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyListDto {
    private long id;
    private String name;
    private String cnpj;
    private String address;
    private String phoneNumber;
    private Integer numberSpacesMotorcycles;
    private Integer numberSpacesCars;
    private Integer occupiedSpacesMotorcycles;
    private Integer occupiedSpacesCar;
}

