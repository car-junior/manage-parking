package com.carjunior.manageparking.domain.entity;

import com.carjunior.manageparking.domain.entity.enums.UserStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder(toBuilder = true)
@Table(name = "user", schema = "dbo")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SequenceGenerator(name = "user_sequence", schema = "dbo", allocationSize = 1)
public class User {
    @Id
    @GeneratedValue(generator = "vehicle_sequence", strategy = GenerationType.SEQUENCE)
    private long id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private UserStatus status;
}
