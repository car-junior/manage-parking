package com.carjunior.manageparking.domain.validators.phonenumber;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.lang.Integer.parseInt;
import static java.lang.String.format;

public class PhoneNumberValidator implements ConstraintValidator<PhoneNumber, String> {
    private final List<Integer> codeDDDs = List.of(
            11, 12, 13, 14, 15, 16, 17, 18, 19,
            21, 22, 24, 27, 28, 31, 32, 33, 34,
            35, 37, 38, 41, 42, 43, 44, 45, 46,
            47, 48, 49, 51, 53, 54, 55, 61, 62,
            64, 63, 65, 66, 67, 68, 69, 71, 73,
            74, 75, 77, 79, 81, 82, 83, 84, 85,
            86, 87, 88, 89, 91, 92, 93, 94, 95,
            96, 97, 98, 99
    );

    private final List<Integer> prefixes = List.of(2, 3, 4, 5, 7);

    @Override
    public boolean isValid(String phoneNumber, ConstraintValidatorContext context) {
        context.disableDefaultConstraintViolation();

        if (phoneNumber == null) {
            context.buildConstraintViolationWithTemplate("is required.").addConstraintViolation();
            return false;
        }

        phoneNumber = phoneNumber.replace("\\d", "");
        var messages = new ArrayList<String>();
        var isValid = true;

        if (phoneNumber.length() < 10 || phoneNumber.length() > 11)
            messages.add("phoneNumber size is invalid.");

        Optional.of(phoneNumber)
                .filter(pn -> pn.length() == 11)
                .map(pn -> parseInt(pn.substring(2, 3)))
                .filter(digit -> digit != 9)
                .ifPresent(digit -> messages.add(
                        format("mobile phoneNumber is invalid. Digit %d no is valid, wait a digit 9.", digit))
                );

        Optional.of(phoneNumber)
                .filter(pn -> pn.length() == 10)
                .map(pn -> parseInt(pn.substring(2, 3)))
                .filter(digit -> !prefixes.contains(digit))
                .ifPresent(digit -> messages.add("fix phoneNumber is invalid"));

        final Integer DDD = parseInt(phoneNumber.substring(0, 2));
        if (!codeDDDs.contains(DDD))
            messages.add(format("DDD(%d) of the phoneNumber is invalid.", DDD));

        if (!messages.isEmpty()) {
            setMessagesConstraintViolation(context, messages);
            isValid = false;
        }

        return isValid;
    }

    private void setMessagesConstraintViolation(ConstraintValidatorContext context, List<String> messages) {
        messages.forEach(m -> context.buildConstraintViolationWithTemplate(m).addConstraintViolation());
    }
}
