package com.carjunior.manageparking.domain.utils;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Path;
import org.springframework.lang.NonNull;

import static com.carjunior.manageparking.domain.utils.Utility.unaccented;

public class CriteriaUtils {
    private static final String UNACCENTED = "UNACCENT";

    private CriteriaUtils() {
    }

    public static <T> Expression<String> lowerAndUnaccented(CriteriaBuilder builder, Path<T> pathAttribute) {
        return builder.lower(builder.function(UNACCENTED, String.class, pathAttribute));
    }

    public static String formatQueryToLike(@NonNull String value) {
        return "%".concat(unaccented(value).trim().toLowerCase()).concat("%");
    }
}
