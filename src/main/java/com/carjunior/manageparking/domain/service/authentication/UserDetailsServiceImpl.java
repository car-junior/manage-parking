package com.carjunior.manageparking.domain.service.authentication;

import com.carjunior.manageparking.domain.repository.UserRepository;
import com.carjunior.manageparking.infrastructure.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        //logger.debug("Entering in loadUserByUsername Method...");
        userRepository.findUserByEmail(email)
                .ifPresentOrElse(user -> {
                    return null ;
                        },
                        () -> {
                            throw CustomException.builder()
                                    .httpStatus(HttpStatus.NOT_FOUND)
                                    .message("Cannot found user with email " + email)
                                    .build();
                        });
    }
}
