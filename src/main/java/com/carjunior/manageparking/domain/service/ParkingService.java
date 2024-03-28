package com.carjunior.manageparking.domain.service;

import com.carjunior.manageparking.domain.entity.Parking;
import com.carjunior.manageparking.domain.repository.ParkingRepository;
import com.carjunior.manageparking.domain.spec.ParkingSpecification;
import com.carjunior.manageparking.domain.spec.search.ParkingSearch;
import com.carjunior.manageparking.infrastructure.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ParkingService {
    private final ParkingRepository parkingRepository;

    public Parking saveParking(Parking parking) {
        validationCreate(parking);
        return parkingRepository.save(parking);
    }

    public Parking getParkingById(Long parkingId) {
        return parkingRepository.findById(parkingId)
                .orElseThrow(() -> CustomException.builder()
                        .httpStatus(HttpStatus.NOT_FOUND)
                        .message(String.format("Cannot found parking with id %d.", parkingId))
                        .build()
                );
    }

    public Parking updateParking(Parking parking) {
        validationUpdate(parking);
        return parkingRepository.save(parking);
    }

    public Page<Parking> getAllParking(ParkingSearch parkingSearch, Pageable pagination) {
        return parkingRepository.findAll(ParkingSpecification.getAll(parkingSearch), pagination);
    }

    // privates methods


    private void validationCreate(Parking parking) {
        assertNotExistsParkingByCnpj(parking.getCnpj(), parking.getId());
    }

    private void validationUpdate(Parking parking) {
        assertExistsParkingById(parking.getId());
        assertNotExistsParkingByCnpj(parking.getCnpj(), parking.getId());
    }

    private void assertNotExistsParkingByCnpj(String cnpj, Long parkingId) {
        if (parkingRepository.existsParkingByCnpjAndIdNot(cnpj, parkingId))
            throw CustomException.builder()
                    .httpStatus(HttpStatus.BAD_REQUEST)
                    .message(String.format("Already parking with this CNPJ: %s", cnpj))
                    .build();
    }

    private void assertExistsParkingById(long parkingId) {
        if (!parkingRepository.existsParkingById(parkingId))
            throw CustomException.builder()
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .message(String.format("Cannot found parking with id %d.", parkingId))
                    .build();
    }

}
