package com.carjunior.manageparking.domain.service;

import com.carjunior.manageparking.domain.entity.User;
import com.carjunior.manageparking.domain.entity.enums.UserStatus;
import com.carjunior.manageparking.domain.repository.UserRepository;
import com.carjunior.manageparking.infrastructure.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User saveUser(User user) {
        validationCreate(user);
        return userRepository.save(user.toBuilder()
                .password(passwordEncoder.encode(user.getPassword()))
                .build()
        );
    }

    public User getUserById(long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> CustomException.builder()
                        .httpStatus(HttpStatus.NOT_FOUND)
                        .message(String.format("Cannot found user with id %d.", userId))
                        .build()
                );
    }

    public void changeStatus(long userId, UserStatus status) {
        assertExistsUserById(userId);
        userRepository.changeStatus(userId, status);
    }

//    public Vehicle getVehicleById(Long vehicleId) {

//    }
//
//    public Vehicle updateVehicle(Vehicle vehicle) {
//        validationUpdate(vehicle);
//        return vehicleRepository.save(vehicle);
//    }
//
//    public Page<Vehicle> getAllVehicle(VehicleSearch vehicleSearch, Pageable pagination) {
//        return vehicleRepository.findAll(VehicleSpecification.getAll(vehicleSearch), pagination);
//    }

    // privates methods


    private void validationCreate(User vehicle) {
//        assertNotExistsVehicleByPlate(vehicle.getPlate(), vehicle.getId());
    }

    //    private void validationUpdate(Vehicle vehicle) {
//        assertExistsVehicleById(vehicle.getId());
//        assertNotExistsVehicleByPlate(vehicle.getPlate(), vehicle.getId());
//    }
//
    private void assertNotExistsUserByEmail(String email, long id) {
        if (userRepository.existsVehicleByEmailAndIdNot(email, id))
            throw CustomException.builder()
                    .httpStatus(HttpStatus.BAD_REQUEST)
                    .message(String.format("Already user with this email: %s", email))
                    .build();
    }

    private void assertExistsUserById(long userId) {
        if (!userRepository.existsUserById(userId))
            throw CustomException.builder()
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .message(String.format("Cannot found user with id %d.", userId))
                    .build();
    }

}
