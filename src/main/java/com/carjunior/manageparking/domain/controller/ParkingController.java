package com.carjunior.manageparking.domain.controller;

import com.carjunior.manageparking.domain.dto.PageResult;
import com.carjunior.manageparking.domain.dto.parking.ParkingCreateUpdateDto;
import com.carjunior.manageparking.domain.dto.parking.ParkingDetailDto;
import com.carjunior.manageparking.domain.dto.parking.ParkingListDto;
import com.carjunior.manageparking.domain.entity.Parking;
import com.carjunior.manageparking.domain.service.ModelMapperService;
import com.carjunior.manageparking.domain.service.ParkingService;
import com.carjunior.manageparking.domain.spec.search.ParkingSearch;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.carjunior.manageparking.domain.utils.Utility.createPagination;

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

    @GetMapping
    public ResponseEntity<PageResult<ParkingListDto>> getAll(
            @RequestParam(name = "q", required = false) String query,
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int itemsPerPage,
            @RequestParam(required = false, defaultValue = "ASC") String sort,
            @RequestParam(required = false, defaultValue = "id") String sortName) {
        var parkingSearch = ParkingSearch.builder().query(query).build();
        var pagination = createPagination(page, itemsPerPage, sort, sortName);
        var result = parkingService.getAllParking(parkingSearch, pagination);
        return ResponseEntity.ok(modelMapperService.toPage(ParkingListDto.class, result));
    }


}
