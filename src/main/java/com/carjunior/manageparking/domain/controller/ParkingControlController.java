package com.carjunior.manageparking.domain.controller;

import com.carjunior.manageparking.domain.dto.parkingcontrol.entrance.EntranceDetailDto;
import com.carjunior.manageparking.domain.dto.parkingcontrol.entrance.EntranceCreateDto;
import com.carjunior.manageparking.domain.dto.parkingcontrol.summary.SummaryDetailDto;
import com.carjunior.manageparking.domain.service.ModelMapperService;
import com.carjunior.manageparking.domain.service.ParkingControlService;
import com.carjunior.manageparking.domain.spec.search.ParkingControlSearch;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

import static org.springframework.format.annotation.DateTimeFormat.ISO.DATE_TIME;

@RestController
@RequiredArgsConstructor
@RequestMapping("/parking-control")
public class ParkingControlController {
    private final ModelMapperService modelMapperService;
    private final ParkingControlService parkingControlService;

    @PostMapping
    public ResponseEntity<EntranceDetailDto> createEntrance(
            @Valid @RequestBody EntranceCreateDto entrance) {
        var parkingControl = parkingControlService.createEntrance(entrance);
        return ResponseEntity.ok(modelMapperService.toObject(EntranceDetailDto.class, parkingControl));
    }

    @PatchMapping("/{parkingControlId}")
    public ResponseEntity<Void> finishEntrance(
            @PathVariable(name = "parkingControlId") Long parkingControlId) {
        parkingControlService.finishEntrance(parkingControlId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("{parkingId}/summary-entrances-exits")
    public ResponseEntity<SummaryDetailDto> getSummaryParkingControl(
            @PathVariable(name = "parkingId") Long parkingId,
            @RequestParam(name = "startAt", required = false) @DateTimeFormat(iso = DATE_TIME) LocalDateTime startAt,
            @RequestParam(name = "endAt", required = false) @DateTimeFormat(iso = DATE_TIME) LocalDateTime endAt) {
        var parkingControlSearch = ParkingControlSearch.builder()
                .startAt(startAt)
                .endAt(endAt)
                .build();
        return ResponseEntity.ok(parkingControlService.getSummaryParkingControl(parkingId, parkingControlSearch));
    }
}