package com.carjunior.manageparking.domain.service;

import com.carjunior.manageparking.domain.entity.Vehicle;
import com.carjunior.manageparking.domain.repository.VehicleRepository;
import com.carjunior.manageparking.domain.spec.VehicleSpecification;
import com.carjunior.manageparking.domain.spec.search.VehicleSearch;
import com.carjunior.manageparking.infrastructure.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VehicleService {
    private final VehicleRepository vehicleRepository;

    public Vehicle saveVehicle(Vehicle vehicle) {
        validationCreate(vehicle);
        return vehicleRepository.save(vehicle);
    }

    public Vehicle getVehicleById(Long vehicleId) {
        return vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> CustomException.builder()
                        .httpStatus(HttpStatus.NOT_FOUND)
                        .message(String.format("Cannot found vehicle with id %d.", vehicleId))
                        .build()
                );
    }

    public Vehicle updateVehicle(Vehicle vehicle) {
        validationUpdate(vehicle);
        return vehicleRepository.save(vehicle);
    }

    public Page<Vehicle> getAllVehicle(VehicleSearch vehicleSearch, Pageable pagination) {
        return vehicleRepository.findAll(VehicleSpecification.getAll(vehicleSearch), pagination);
    }

    // privates methods


    private void validationCreate(Vehicle vehicle) {
        assertNotExistsVehicleByPlate(vehicle.getPlate(), vehicle.getId());
    }

    private void validationUpdate(Vehicle vehicle) {
        assertExistsVehicleById(vehicle.getId());
        assertNotExistsVehicleByPlate(vehicle.getPlate(), vehicle.getId());
    }

    private void assertNotExistsVehicleByPlate(String plate, Long vehicleId) {
        if (vehicleRepository.existsVehicleByPlateAndIdNot(plate, vehicleId))
            throw CustomException.builder()
                    .httpStatus(HttpStatus.BAD_REQUEST)
                    .message(String.format("Already vehicle with this Plate: %s", plate))
                    .build();
    }

    private void assertExistsVehicleById(long vehicleId) {
        if (!vehicleRepository.existsVehicleById(vehicleId))
            throw CustomException.builder()
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .message(String.format("Cannot found vehicle with id %d.", vehicleId))
                    .build();
    }

}
