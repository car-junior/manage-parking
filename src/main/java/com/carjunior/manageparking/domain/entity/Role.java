package com.carjunior.manageparking.domain.entity;

import com.carjunior.manageparking.domain.entity.enums.RoleType;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Entity
@Getter
@Setter
@Builder(toBuilder = true)
@Table(name = "role", schema = "dbo")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SequenceGenerator(name = "role_sequence", schema = "dbo", allocationSize = 1)
public class Role {
    @Id
    @GeneratedValue(generator = "role_sequence", strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private RoleType type;

    @ManyToOne
    @JoinColumn(name = "parking_id", nullable = false)
    private Parking parking;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return id == role.id && name.equals(role.name) && type.equals(role.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, type);
    }
}
