package com.application.constant.persons;

public final class PersonsJpql {
    public static final String UPDATE_PERSON = "update Persons p set p.fullName = ?1," +
            " p.dateOfBirth = ?2, p.gender = ?3, p.email = ?4, p.phoneNumber = ?5 where p.personId = ?6";
    public static final String DELETE_PERSON = "delete from Persons where personId = ?1";

    private PersonsJpql() {
    }
}
