package com.application.util;

import com.application.entity.Persons;

public final class ValidationUtil {

    private ValidationUtil() {}

    public static boolean isPersonNull(Persons person) {
        return (person == null)
                || (person.getFullName() == null)
                || (person.getDateOfBirth() == null)
                || (person.getGender() == null)
                || (person.getEmail() == null)
                || (person.getPhoneNumber() == 0);
    }
}