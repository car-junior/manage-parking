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
import java.util.List;

public interface ParkingControlRepository extends JpaRepository<ParkingControl, Long>, JpaSpecificationExecutor<ParkingControl> {
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("UPDATE ParkingControl pc SET pc.exitDateTime = :exitDatetime, pc.status = :status WHERE pc.id = :id")
    void finishEntrance(@Param("id") long id, @Param("status") ParkingControlStatus status, @Param("exitDatetime") LocalDateTime exitDatetime);

    @Query(value = """
            WITH pc_entrance AS (SELECT DATE_TRUNC('HOUR', pc.entry_datetime) AS hour,
                                        COUNT(pc)                             AS quantity,
                                        'ENTRANCE'                            AS type
                                 FROM dbo.parking_control pc
                                 WHERE entry_datetime IS NOT NULL
                                 GROUP BY DATE_TRUNC('HOUR', pc.entry_datetime)),
                 pc_exit AS (SELECT DATE_TRUNC('HOUR', pc.exit_datetime) AS hour,
                                    COUNT(pc)                            AS quantity,
                                    'EXIT'                               AS type
                             FROM dbo.parking_control pc
                             WHERE exit_datetime IS NOT NULL
                             GROUP BY DATE_TRUNC('HOUR', pc.exit_datetime))
                (SELECT *
                 FROM pc_entrance
                 UNION ALL
                 SELECT *
                 FROM pc_exit) ORDER BY hour;
            """, nativeQuery = true)
    List<Object> getSummaryParkingControlPerHour();
}
