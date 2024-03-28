package com.carjunior.manageparking.domain.repository;

import com.carjunior.manageparking.domain.entity.Parking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ParkingRepository extends JpaRepository<Parking, Long>, JpaSpecificationExecutor<Parking> {
    boolean existsParkingByCnpjAndIdNot(String cnpj, long id);
    boolean existsParkingById(long id);
}
