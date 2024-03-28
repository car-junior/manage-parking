package com.carjunior.manageparking.domain.validators.phone;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static java.lang.Integer.parseInt;

public class PhoneBRValidator implements ConstraintValidator<PhoneBR, String> {
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
    private final Integer sizeLandlinePhone = 10;
    private final Integer sizeMobilePhone = 11;

    @Override
    public boolean isValid(String phoneNumber, ConstraintValidatorContext context) {
        context.disableDefaultConstraintViolation();

        if (phoneNumber == null) {
            context.buildConstraintViolationWithTemplate("is required.").addConstraintViolation();
            return false;
        }

        if (isInvalidSizePhoneNumber(phoneNumber)) {
            context.buildConstraintViolationWithTemplate("phoneNumber size is invalid.").addConstraintViolation();
            return false;
        }

        phoneNumber = phoneNumber.replace("\\d", "");
        var messages = new ArrayList<String>();
        var isValid = true;

        if (isInvalidDDD(phoneNumber))
            messages.add("DDD of the phoneNumber is invalid.");

        if (phoneNumber.length() == sizeMobilePhone) {
            if (isMobilePhoneInvalidNineDigit(phoneNumber))
                messages.add("mobile phoneNumber is invalid. Nine-digit is invalid, wait a digit 9.");

            if (isInvalidMobilePhoneRepeated(phoneNumber))
                messages.add("mobile phoneNumber is invalid.");
        } else {
            if (isLandLinePhoneInvalidPrefix(phoneNumber))
                messages.add("prefix landLine phoneNumber is invalid.");

            if (isInvalidLandLinePhoneRepeated(phoneNumber))
                messages.add("landLine phoneNumber is invalid.");
        }

        if (!messages.isEmpty()) {
            setMessagesConstraintViolation(context, messages);
            isValid = false;
        }

        return isValid;
    }

    private void setMessagesConstraintViolation(ConstraintValidatorContext context, List<String> messages) {
        messages.forEach(m -> context.buildConstraintViolationWithTemplate(m).addConstraintViolation());
    }

    private boolean isInvalidSizePhoneNumber(String phoneNumber) {
        return phoneNumber.length() < sizeLandlinePhone || phoneNumber.length() > sizeMobilePhone;
    }

    private boolean isMobilePhoneInvalidNineDigit(String phoneNumber) {
        final int nineDigit = 9;
        return parseInt(phoneNumber.substring(2, 3)) != nineDigit;
    }

    private boolean isLandLinePhoneInvalidPrefix(String phoneNumber) {
        return !prefixes.contains(parseInt(phoneNumber.substring(2, 3)));
    }

    private boolean isInvalidDDD(String phoneNumber) {
        return !codeDDDs.contains(parseInt(phoneNumber.substring(0, 2)));
    }

    private boolean isInvalidMobilePhoneRepeated(String phoneNumber) {
        final String mobilePhoneJustWithDigitsNine = "99999999999";
        return isEqualsPhoneSequenceWithPhoneNumber(phoneNumber)
                && !phoneNumber.equals(mobilePhoneJustWithDigitsNine);
    }

    private boolean isInvalidLandLinePhoneRepeated(String phoneNumber) {
        return isEqualsPhoneSequenceWithPhoneNumber(phoneNumber)
                && (isLandLinePhoneInvalidPrefix(phoneNumber) || isInvalidDDD(phoneNumber));
    }

    private boolean isEqualsPhoneSequenceWithPhoneNumber(String phoneNumber) {
        final String sizeSequence = "{" + phoneNumber.length() + "}";
        final Pattern pattern = Pattern.compile(phoneNumber.charAt(0) + sizeSequence);
        return pattern.matcher(phoneNumber).find();
    }
}
