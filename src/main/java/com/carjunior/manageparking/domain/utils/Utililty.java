package com.carjunior.manageparking.domain.utils;

import org.springframework.lang.NonNull;

public final class Utililty {
    private Utililty() {}

    public static String onlyNumbers(@NonNull String value) {
        return value.replace("\\d", "");
    }
}
