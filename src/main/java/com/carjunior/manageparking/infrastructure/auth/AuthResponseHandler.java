package com.carjunior.manageparking.infrastructure.auth;

import jakarta.servlet.http.HttpServletResponse;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponseHandler {
    private int status;
    private String message;
}
