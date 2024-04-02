package com.carjunior.manageparking.domain.controller;

import com.carjunior.manageparking.domain.dto.user.UserCreateDto;
import com.carjunior.manageparking.domain.dto.user.UserDetailDto;
import com.carjunior.manageparking.domain.entity.User;
import com.carjunior.manageparking.domain.service.ModelMapperService;
import com.carjunior.manageparking.domain.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final ModelMapperService modelMapperService;

    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody UserCreateDto userCreate) {
        var user = userService.saveUser(modelMapperService.toObject(User.class, userCreate));
        return ResponseEntity.ok(modelMapperService.toObject(UserDetailDto.class, user));
    }

//    @PutMapping("/{vehicleId}")
//    public ResponseEntity<VehicleDetailDto> update(
//            @PathVariable(name = "vehicleId") Long vehicleId,
//            @Valid @RequestBody VehicleCreateUpdateDto vehicleUpdateDto) {
//        var vehicle = modelMapperService.toObject(Vehicle.class, vehicleUpdateDto)
//                .toBuilder()
//                .id(vehicleId)
//                .build();
//        vehicle = vehicleService.updateVehicle(vehicle);
//        return ResponseEntity.ok(modelMapperService.toObject(VehicleDetailDto.class, vehicle));
//    }
//
//    @GetMapping("/{vehicleId}")
//    public ResponseEntity<VehicleDetailDto> getById(@PathVariable(name = "vehicleId") Long vehicleId) {
//        return ResponseEntity
//                .ok(modelMapperService.toObject(VehicleDetailDto.class, vehicleService.getVehicleById(vehicleId)));
//    }
//
//    @GetMapping
//    public ResponseEntity<PageResult<VehicleListDto>> getAll(
//            @RequestParam(name = "q", required = false) String query,
//            @RequestParam(name = "type", required = false) VehicleType type,
//            @RequestParam(required = false, defaultValue = "0") int page,
//            @RequestParam(required = false, defaultValue = "10") int itemsPerPage,
//            @RequestParam(required = false, defaultValue = "ASC") String sort,
//            @RequestParam(required = false, defaultValue = "id") String sortName) {
//        var vehicleSearch = VehicleSearch.builder()
//                .query(query)
//                .type(type)
//                .build();
//        var pagination = createPagination(page, itemsPerPage, sort, sortName);
//        var result = vehicleService.getAllVehicle(vehicleSearch, pagination);
//        return ResponseEntity.ok(modelMapperService.toPage(VehicleListDto.class, result));
//    }


}
