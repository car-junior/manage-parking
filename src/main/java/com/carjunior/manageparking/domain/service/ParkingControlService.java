package com.carjunior.manageparking.domain.service;

import com.carjunior.manageparking.domain.dto.parkingcontrol.VehicleEntranceDto;
import com.carjunior.manageparking.domain.entity.Parking;
import com.carjunior.manageparking.domain.entity.ParkingControl;
import com.carjunior.manageparking.domain.entity.Vehicle;
import com.carjunior.manageparking.domain.entity.enums.ParkingControlStatus;
import com.carjunior.manageparking.domain.repository.ParkingControlRepository;
import com.carjunior.manageparking.domain.repository.ParkingRepository;
import com.carjunior.manageparking.domain.repository.VehicleRepository;
import com.carjunior.manageparking.infrastructure.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ParkingControlService {
    private final ParkingRepository parkingRepository;
    private final VehicleRepository vehicleRepository;
    private final ParkingControlRepository parkingControlRepository;

    @Transactional
    public ParkingControl createEntrance(VehicleEntranceDto vehicleEntrance) {
        final Parking parking = getParkingById(vehicleEntrance.parkingId());
        final Vehicle vehicle = getVehicleByPlate(vehicleEntrance.vehiclePlate());
        return parkingControlRepository.save(ParkingControl.builder()
                .parking(parking)
                .vehicle(vehicle)
                .entryDateTime(LocalDateTime.now())
                .build()
        );
    }

    public void finishEntrance(Long parkingControlId) {
        var parkingControl = getParkingControlById(parkingControlId);
        assertExistsEntranceInOpen(parkingControl);
        parkingControlRepository.finishEntrance(parkingControlId, ParkingControlStatus.FINALIZED, LocalDateTime.now());
    }

    public ParkingControl getParkingControlById(long parkingControlId) {
        return parkingControlRepository.findById(parkingControlId)
                .orElseThrow(() -> CustomException.builder()
                        .httpStatus(HttpStatus.NOT_FOUND)
                        .message(String.format("Cannot found parking control with id %d.", parkingControlId))
                        .build()
                );
    }

    private Parking getParkingById(long parkingId) {
        return parkingRepository.getParkingById(parkingId)
                .orElseThrow(() -> CustomException.builder()
                        .httpStatus(HttpStatus.NOT_FOUND)
                        .message(String.format("Cannot found parking with id %d.", parkingId))
                        .build()
                );
    }

    private Vehicle getVehicleByPlate(String plate) {
        return vehicleRepository.getVehicleByPlate(plate)
                .orElseThrow(() -> CustomException.builder()
                        .httpStatus(HttpStatus.NOT_FOUND)
                        .message(String.format("Cannot found vehicle with plate %s.", plate))
                        .build()
                );
    }

    private void assertExistsParkingControl(long parkingControlId) {
        if (parkingControlRepository.existsById(parkingControlId))
            throw CustomException.builder()
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .message(String.format("Cannot found parking control with id %s.", parkingControlId))
                    .build();
    }

    private void assertExistsEntranceInOpen(ParkingControl parkingControl) {
        final boolean isNotParked = parkingControl.getStatus() != ParkingControlStatus.PARKED;
        final boolean notHaveEntryDatetime = parkingControl.getEntryDateTime() == null;
        final boolean haveExitDatetime = parkingControl.getExitDateTime() != null;
        if (isNotParked || notHaveEntryDatetime || haveExitDatetime)
            throw CustomException.builder()
                    .httpStatus(HttpStatus.BAD_REQUEST)
                    .message("Cannot finalize your exit. Contact the support.")
                    .build();
    }
}
