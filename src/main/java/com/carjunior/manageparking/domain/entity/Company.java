package com.carjunior.manageparking.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@Builder(toBuilder = true)
@Table(name = "company", schema = "dbo")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SequenceGenerator(name = "company_sequence", schema = "dbo", allocationSize = 1)
public class Company {
    @Id
    @GeneratedValue(generator = "company_sequence", strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "cnpj", nullable = false)
    private String cnpj;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "number_spaces_motorcycles", nullable = false)
    private Integer numberSpacesMotorcycles;

    @Column(name = "occupied_spaces_motorcycles")
    private Integer occupiedSpacesMotorcycles;

    @Column(name = "number_spaces_cars", nullable = false)
    private Integer numberSpacesCars;

    @Column(name = "occupied_spaces_cars")
    private Integer occupiedSpacesCars;

    @OneToMany
    @Builder.Default
    List<ParkingControl> parkingControls = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return id == company.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
