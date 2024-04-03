package com.carjunior.manageparking.domain.service;

import com.carjunior.manageparking.domain.entity.User;
import com.carjunior.manageparking.domain.entity.enums.UserStatus;
import com.carjunior.manageparking.domain.repository.UserRepository;
import com.carjunior.manageparking.domain.spec.UserSpecification;
import com.carjunior.manageparking.domain.spec.search.UserSearch;
import com.carjunior.manageparking.infrastructure.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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


    public User updateUser(User user) {
        validationUpdate(user);
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

    public Page<User> getAllUser(UserSearch vehicleSearch, Pageable pagination) {
        return userRepository.findAll(UserSpecification.getAll(vehicleSearch), pagination);
    }

    public void changeStatus(long userId, UserStatus status) {
        assertExistsUserById(userId);
        userRepository.changeStatus(userId, status);
    }

    // privates methods


    private void validationCreate(User user) {
        assertNotExistsUserByEmail(user.getName(), user.getId());
    }

    private void validationUpdate(User user) {
        assertExistsUserById(user.getId());
        assertNotExistsUserByEmail(user.getEmail(), user.getId());
    }

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
