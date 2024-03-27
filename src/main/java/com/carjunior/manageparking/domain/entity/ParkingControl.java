package com.carjunior.manageparking.domain.entity;

import com.carjunior.manageparking.domain.entity.enums.ParkingControlStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
@Setter
@Builder(toBuilder = true)
@Table(name = "parking_control", schema = "dbo")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SequenceGenerator(name = "parking_control_sequence", schema = "dbo", allocationSize = 1)
public class ParkingControl {
    @Id
    @GeneratedValue(generator = "parking_control_sequence", strategy = GenerationType.SEQUENCE)
    private long id;

    @ManyToOne
    @JoinColumn(name = "parking_id")
    private Parking parking;

    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    @Builder.Default
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ParkingControlStatus status = ParkingControlStatus.PARKED;

    @Column(name = "entry_date_time")
    private LocalDateTime entryDateTime;

    @Column(name = "exit_date_time")
    private LocalDateTime exitDateTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParkingControl parkingControl = (ParkingControl) o;
        return id == parkingControl.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
