package com.carjunior.manageparking.domain.service;

import com.carjunior.manageparking.domain.entity.Role;
import com.carjunior.manageparking.domain.repository.ParkingRepository;
import com.carjunior.manageparking.domain.repository.RoleRepository;
import com.carjunior.manageparking.infrastructure.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    private final ParkingRepository parkingRepository;
    public Role saveRole(Role role) {
        validationCreate(role);
        return roleRepository.save(role);
    }


//    public User updateUser(User user) {
//        validationUpdate(user);
//        return userRepository.save(user.toBuilder()
//                .password(passwordEncoder.encode(user.getPassword()))
//                .build()
//        );
//    }

    public Role getRoleById(long roleId) {
        return roleRepository.findById(roleId)
                .orElseThrow(() -> CustomException.builder()
                        .httpStatus(HttpStatus.NOT_FOUND)
                        .message(String.format("Cannot found role with id %d.", roleId))
                        .build()
                );
    }

//    public Page<User> getAllUser(UserSearch vehicleSearch, Pageable pagination) {
//        return userRepository.findAll(UserSpecification.getAll(vehicleSearch), pagination);
//    }

    // privates methods

    private void validationCreate(Role role) {
        assertExistsParkingById(role.getParking().getId());
        assertNotExistsRoleByName(role.getName(), role.getId());
    }

//
//    private void validationUpdate(User user) {
//        assertExistsUserById(user.getId());
//        assertNotExistsUserByEmail(user.getEmail(), user.getId());
//    }

    private void assertNotExistsRoleByName(String name, long id) {
        if (roleRepository.existsRoleByNameAndIdNot(name, id))
            throw CustomException.builder()
                    .httpStatus(HttpStatus.BAD_REQUEST)
                    .message(String.format("Already role with this name: %s", name))
                    .build();
    }

    private void assertExistsParkingById(long parkingId) {
        if (!parkingRepository.existsParkingById(parkingId))
            throw CustomException.builder()
                    .httpStatus(HttpStatus.BAD_REQUEST)
                    .message(String.format("Cannot found parking with id: %d", parkingId))
                    .build();
    }

//    private void assertExistsUserById(long userId) {
//        if (!userRepository.existsUserById(userId))
//            throw CustomException.builder()
//                    .httpStatus(HttpStatus.NOT_FOUND)
//                    .message(String.format("Cannot found user with id %d.", userId))
//                    .build();
//    }

}
