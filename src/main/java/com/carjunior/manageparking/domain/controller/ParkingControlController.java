package com.carjunior.manageparking.domain.controller;

import com.carjunior.manageparking.domain.dto.parkingcontrol.VehicleEntranceDetailDto;
import com.carjunior.manageparking.domain.dto.parkingcontrol.VehicleEntranceDto;
import com.carjunior.manageparking.domain.service.ModelMapperService;
import com.carjunior.manageparking.domain.service.ParkingControlService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/parking-control")
public class ParkingControlController {
    private final ModelMapperService modelMapperService;
    private final ParkingControlService parkingControlService;

    @PostMapping
    public ResponseEntity<VehicleEntranceDetailDto> vehicleEntrance(@Valid @RequestBody VehicleEntranceDto vehicleEntrance) {
        var parkingControl = parkingControlService.createEntrance(vehicleEntrance);
        return ResponseEntity.ok(modelMapperService.toObject(VehicleEntranceDetailDto.class, parkingControl));
    }
}
