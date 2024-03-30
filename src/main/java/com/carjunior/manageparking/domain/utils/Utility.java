package com.carjunior.manageparking.domain.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.lang.NonNull;

import java.text.Normalizer;
import java.util.Optional;

public final class Utility {
    private Utility() {}

    public static boolean isPresent(Object value) {
        return Optional.ofNullable(value).isPresent();
    }
    public static boolean isEmpty(Object value) {
        return Optional.ofNullable(value).isEmpty();
    }

    public static String onlyNumbers(@NonNull String value) {
        return value.replace("\\d", "");
    }

    public static String onlyAlphaNumbers(@NonNull String value) {
        return value.replaceAll("[^A-Za-z0-9]", "");
    }

    public static String unaccented(String src) {
        return Normalizer
                .normalize(src, Normalizer.Form.NFD)
                .replaceAll("[^\\p{ASCII}]", "");
    }

    public static ObjectMapper objectMapperForJson() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper;
    }
    public static PageRequest createPagination(int page, int itemsPerPage, String sort, String sortName) {
        return PageRequest.of(page, itemsPerPage, Sort.by(Sort.Direction.fromString(sort), sortName));
    }
}
