package com.carjunior.manageparking.domain.controller;

import com.carjunior.manageparking.domain.dto.parking.ParkingCreateUpdateDto;
import com.carjunior.manageparking.domain.dto.parking.ParkingDetailDto;
import com.carjunior.manageparking.domain.entity.Parking;
import com.carjunior.manageparking.domain.service.ModelMapperService;
import com.carjunior.manageparking.domain.service.ParkingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/parking-lots")
public class ParkingController {
    private final ParkingService parkingService;
    private final ModelMapperService modelMapperService;

    @PostMapping
    public ResponseEntity<ParkingDetailDto> create(@Valid @RequestBody ParkingCreateUpdateDto parkingCreateDto) {
        var parking = parkingService.saveParking(modelMapperService.toObject(Parking.class, parkingCreateDto));
        return ResponseEntity.ok(modelMapperService.toObject(ParkingDetailDto.class, parking));
    }

    @PutMapping("/{parkingId}")
    public ResponseEntity<ParkingDetailDto> update(
            @PathVariable(name = "parkingId") Long parkingId,
            @Valid @RequestBody ParkingCreateUpdateDto parkingUpdateDto) {
        var parking = modelMapperService.toObject(Parking.class, parkingUpdateDto)
                .toBuilder()
                .id(parkingId)
                .build();
        parking = parkingService.updateParking(parking);
        return ResponseEntity.ok(modelMapperService.toObject(ParkingDetailDto.class, parking));
    }

    @GetMapping("/{parkingId}")
    public ResponseEntity<ParkingDetailDto> getById(@PathVariable(name = "parkingId") Long parkingId) {
        return ResponseEntity
                .ok(modelMapperService.toObject(ParkingDetailDto.class, parkingService.getParkingById(parkingId)));
    }

}
