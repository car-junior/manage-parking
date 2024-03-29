package com.carjunior.manageparking.domain.controller;

import com.carjunior.manageparking.domain.dto.parkingcontrol.VehicleEntranceDetailDto;
import com.carjunior.manageparking.domain.dto.parkingcontrol.VehicleEntranceDto;
import com.carjunior.manageparking.domain.service.ModelMapperService;
import com.carjunior.manageparking.domain.service.ParkingControlService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/parking-control")
public class ParkingControlController {
    private final ModelMapperService modelMapperService;
    private final ParkingControlService parkingControlService;

    @PostMapping
    public ResponseEntity<VehicleEntranceDetailDto> vehicleEntrance(
            @Valid @RequestBody VehicleEntranceDto vehicleEntrance) {
        var parkingControl = parkingControlService.createEntrance(vehicleEntrance);
        return ResponseEntity.ok(modelMapperService.toObject(VehicleEntranceDetailDto.class, parkingControl));
    }

    @PatchMapping("/{parkingControlId}")
    public ResponseEntity<Void> vehicleExit(
            @PathVariable(name = "parkingControlId") Long parkingControlId) {
        parkingControlService.finishEntrance(parkingControlId);
        return ResponseEntity.noContent().build();
    }
}
