package com.carjunior.manageparking.domain.controller;

import com.carjunior.manageparking.domain.dto.parking.ParkingCreateUpdateDto;
import com.carjunior.manageparking.domain.dto.parking.ParkingDetailDto;
import com.carjunior.manageparking.domain.entity.Parking;
import com.carjunior.manageparking.domain.service.ModelMapperService;
import com.carjunior.manageparking.domain.service.ParkingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/parking-lots")
public class ParkingController {
    private final ParkingService parkingService;
    private final ModelMapperService modelMapperService;

    @PostMapping
    @PreAuthorize("hasAuthority('PARKING_CREATE')")
    public ResponseEntity<ParkingDetailDto> create(@Valid @RequestBody ParkingCreateUpdateDto parkingCreateDto) {
        var parking = parkingService.saveParking(modelMapperService.toObject(Parking.class, parkingCreateDto));
        return ResponseEntity.ok(modelMapperService.toObject(ParkingDetailDto.class, parking));
    }

    // hasAuthority
    // PreHasAuthority
    // PreAnyAuthority
    // PreAndAuthority
    // hasAnyAuthority
    @PutMapping("/{parkingId}")
    @PreAuthorize("hasAuthority('PARKING_UPDATE')")
    public ResponseEntity<ParkingDetailDto> update(
            @PathVariable(name = "parkingId") long parkingId,
            @Valid @RequestBody ParkingCreateUpdateDto parkingUpdateDto) {
        var parking = modelMapperService.toObject(Parking.class, parkingUpdateDto)
                .toBuilder()
                .id(parkingId)
                .build();
        parking = parkingService.updateParking(parking);
        return ResponseEntity.ok(modelMapperService.toObject(ParkingDetailDto.class, parking));
    }

    @GetMapping("/{parkingId}")
    @PreAuthorize("hasAuthority('PARKING_DETAIL')")
    public ResponseEntity<ParkingDetailDto> getById(@PathVariable(name = "parkingId") long parkingId) {
        return ResponseEntity
                .ok(modelMapperService.toObject(ParkingDetailDto.class, parkingService.getParkingById(parkingId)));
    }

}
