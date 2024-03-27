package com.carjunior.manageparking.domain.service;

import com.carjunior.manageparking.domain.entity.Parking;
import com.carjunior.manageparking.domain.repository.ParkingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ParkingService {
    private final ParkingRepository parkingRepository;

    public Parking saveParking(Parking parking) {
        return parkingRepository.save(parking);
    }

    public Parking getParkingById(Long parkingId) {
        return parkingRepository.findById(parkingId).get();
    }

    public List<Parking> getAllParking() {
        return parkingRepository.findAll();
    }
}
