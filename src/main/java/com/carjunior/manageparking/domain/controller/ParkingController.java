package com.carjunior.manageparking.domain.controller;

import com.carjunior.manageparking.domain.dto.parking.ParkingCreateDto;
import com.carjunior.manageparking.domain.dto.parking.ParkingDetailDto;
import com.carjunior.manageparking.domain.dto.parking.ParkingListDto;
import com.carjunior.manageparking.domain.entity.Parking;
import com.carjunior.manageparking.domain.service.ParkingService;
import com.carjunior.manageparking.domain.service.ModelMapperService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/parking-lots")
public class ParkingController {
    private final ParkingService parkingService;
    private final ModelMapperService modelMapperService;

    @PostMapping
    public ResponseEntity<ParkingDetailDto> create(@Valid @RequestBody ParkingCreateDto parkingCreateDto) {
        var parking = parkingService.saveParking(modelMapperService.toObject(Parking.class, parkingCreateDto));
        return ResponseEntity.ok(modelMapperService.toObject(ParkingDetailDto.class, parking));
    }

    @GetMapping("/{parkingId}")
    public ResponseEntity<ParkingDetailDto> getById(@PathVariable(name = "parkingId") Long parkingId) {
        return ResponseEntity.ok(
                modelMapperService.toObject(ParkingDetailDto.class, parkingService.getParkingById(parkingId))
        );
    }

    @GetMapping
    public ResponseEntity<List<ParkingListDto>> getAll() {
        return ResponseEntity.ok(modelMapperService.toList(ParkingListDto.class, parkingService.getAllParking()));
    }


}
