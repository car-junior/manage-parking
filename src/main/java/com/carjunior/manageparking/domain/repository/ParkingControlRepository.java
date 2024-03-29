package com.carjunior.manageparking.domain.repository;

import com.carjunior.manageparking.domain.entity.ParkingControl;
import com.carjunior.manageparking.domain.entity.enums.ParkingControlStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

public interface ParkingControlRepository extends JpaRepository<ParkingControl, Long>, JpaSpecificationExecutor<ParkingControl> {
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("UPDATE ParkingControl pc SET pc.exitDateTime = :exitDatetime, pc.status = :status WHERE pc.id = :id")
    void finishEntrance(@Param("id") long id,
                        @Param("status") ParkingControlStatus status,
                        @Param("exitDatetime") LocalDateTime exitDatetime);
}
