package com.carjunior.manageparking.domain.repository;

import com.carjunior.manageparking.domain.entity.ParkingControl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ParkingControlRepository extends JpaRepository<ParkingControl, Long>, JpaSpecificationExecutor<ParkingControl> {

}
