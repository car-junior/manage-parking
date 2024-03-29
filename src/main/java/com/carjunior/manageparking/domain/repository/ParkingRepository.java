package com.carjunior.manageparking.domain.repository;

import com.carjunior.manageparking.domain.entity.Parking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ParkingRepository extends JpaRepository<Parking, Long>, JpaSpecificationExecutor<Parking> {
    boolean existsParkingByCnpjAndIdNot(String cnpj, long id);
    boolean existsParkingById(long id);

    @Query("SELECT new Parking(p.id, p.name) FROM Parking p WHERE p.id = :id")
    Optional<Parking> getParkingById(@Param("id") long id);
}
