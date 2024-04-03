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
@Table(name = "parking", schema = "dbo")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SequenceGenerator(name = "parking_sequence", schema = "dbo", allocationSize = 1)
public class Parking {
    @Id
    @GeneratedValue(generator = "parking_sequence", strategy = GenerationType.SEQUENCE)
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

    @OneToMany(mappedBy = "parking")
    @Builder.Default
    private List<ParkingControl> parkingControls = new ArrayList<>();

    @OneToMany(mappedBy = "parking")
    @Builder.Default
    private List<Role> roles = new ArrayList<>();
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Parking parking = (Parking) o;
        return id == parking.id;
    }

    public Parking(long id, String name) {
        this.id = id;
        this.name = name;
    }
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
