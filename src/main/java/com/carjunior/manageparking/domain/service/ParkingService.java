package com.carjunior.manageparking.domain.service;

import com.carjunior.manageparking.domain.entity.Parking;
import com.carjunior.manageparking.domain.repository.ParkingRepository;
import com.carjunior.manageparking.infrastructure.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ParkingService {
    private final ParkingRepository parkingRepository;

    public Parking saveParking(Parking parking) {
        validation(parking);
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

    public List<Parking> getAllParking() {
        return parkingRepository.findAll();
    }

    // privates methods


    private void validation(Parking parking) {
        assertNotExistsParking(parking.getCnpj());
    }

    private void assertNotExistsParking(String cnpj) {
        if (parkingRepository.existsParkingByCnpj(cnpj))
            throw CustomException.builder()
                    .httpStatus(HttpStatus.BAD_REQUEST)
                    .message(String.format("Already parking with this CNPJ: %s", cnpj))
                    .build();
    }
}
