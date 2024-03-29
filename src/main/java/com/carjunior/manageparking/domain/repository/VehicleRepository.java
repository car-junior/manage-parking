package com.carjunior.manageparking.domain.repository;

import com.carjunior.manageparking.domain.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface VehicleRepository extends JpaRepository<Vehicle, Long>, JpaSpecificationExecutor<Vehicle> {
    boolean existsVehicleByPlateAndIdNot(String plate, long id);
    boolean existsVehicleById(long id);

    @Query("SELECT new Vehicle(v.id, v.model, v.plate, v.type) FROM Vehicle v WHERE v.plate = :plate")
    Optional<Vehicle> getVehicleByPlate(@Param("plate") String plate);
}
