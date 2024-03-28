package com.carjunior.manageparking.domain.repository;

import com.carjunior.manageparking.domain.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface VehicleRepository extends JpaRepository<Vehicle, Long>, JpaSpecificationExecutor<Vehicle> {
    boolean existsVehicleByPlateAndIdNot(String plate, long id);
    boolean existsVehicleById(long id);
}
