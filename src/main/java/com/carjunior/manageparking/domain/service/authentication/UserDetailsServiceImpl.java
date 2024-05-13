package com.carjunior.manageparking.domain.service.authentication;

import com.carjunior.manageparking.domain.entity.security.CustomUserDetails;
import com.carjunior.manageparking.domain.repository.UserRepository;
import com.carjunior.manageparking.infrastructure.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicReference;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var userDetails = new AtomicReference<CustomUserDetails>();

        userRepository.findUserByEmail(email)
                .ifPresentOrElse(
                        user -> userDetails.set(
                                CustomUserDetails.builder()
                                        .username(user.getEmail())
                                        .password(user.getPassword())
                                        .authorities(user.getPermissions())
                                        .build()
                        ),
                        () -> {
                            throw CustomException.builder()
                                    .httpStatus(HttpStatus.NOT_FOUND)
                                    .message("Cannot found user with email " + email)
                                    .build();
                        }
                );

        return userDetails.get();
    }
}
