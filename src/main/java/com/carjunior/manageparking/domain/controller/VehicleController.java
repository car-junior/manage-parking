package com.carjunior.manageparking.domain.controller;

import com.carjunior.manageparking.domain.dto.PageResult;
import com.carjunior.manageparking.domain.dto.vehicle.VehicleCreateUpdateDto;
import com.carjunior.manageparking.domain.dto.vehicle.VehicleDetailDto;
import com.carjunior.manageparking.domain.dto.vehicle.VehicleListDto;
import com.carjunior.manageparking.domain.entity.Vehicle;
import com.carjunior.manageparking.domain.entity.enums.VehicleType;
import com.carjunior.manageparking.domain.service.ModelMapperService;
import com.carjunior.manageparking.domain.service.VehicleService;
import com.carjunior.manageparking.domain.spec.search.VehicleSearch;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.carjunior.manageparking.domain.utils.Utility.createPagination;

@RestController
@RequiredArgsConstructor
@RequestMapping("/vehicles")
public class VehicleController {
    private final VehicleService vehicleService;
    private final ModelMapperService modelMapperService;

    @PostMapping
    public ResponseEntity<VehicleDetailDto> create(@Valid @RequestBody VehicleCreateUpdateDto vehicleCreateDto) {
        var vehicle = vehicleService.saveVehicle(modelMapperService.toObject(Vehicle.class, vehicleCreateDto));
        return ResponseEntity.ok(modelMapperService.toObject(VehicleDetailDto.class, vehicle));
    }

    @PutMapping("/{vehicleId}")
    public ResponseEntity<VehicleDetailDto> update(
            @PathVariable(name = "vehicleId") long vehicleId,
            @Valid @RequestBody VehicleCreateUpdateDto vehicleUpdateDto) {
        var vehicle = modelMapperService.toObject(Vehicle.class, vehicleUpdateDto)
                .toBuilder()
                .id(vehicleId)
                .build();
        vehicle = vehicleService.updateVehicle(vehicle);
        return ResponseEntity.ok(modelMapperService.toObject(VehicleDetailDto.class, vehicle));
    }

    @GetMapping("/{vehicleId}")
    public ResponseEntity<VehicleDetailDto> getById(@PathVariable(name = "vehicleId") long vehicleId) {
        return ResponseEntity
                .ok(modelMapperService.toObject(VehicleDetailDto.class, vehicleService.getVehicleById(vehicleId)));
    }

    @GetMapping
    public ResponseEntity<PageResult<VehicleListDto>> getAll(
            @RequestParam(name = "q", required = false) String query,
            @RequestParam(name = "type", required = false) VehicleType type,
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int itemsPerPage,
            @RequestParam(required = false, defaultValue = "ASC") String sort,
            @RequestParam(required = false, defaultValue = "id") String sortName) {
        var vehicleSearch = VehicleSearch.builder()
                .query(query)
                .type(type)
                .build();
        var pagination = createPagination(page, itemsPerPage, sort, sortName);
        var result = vehicleService.getAllVehicle(vehicleSearch, pagination);
        return ResponseEntity.ok(modelMapperService.toPage(VehicleListDto.class, result));
    }


}
