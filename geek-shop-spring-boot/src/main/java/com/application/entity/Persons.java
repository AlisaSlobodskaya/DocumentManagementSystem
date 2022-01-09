package com.application.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "persons")
public class Persons {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int personId;

    @Column(name = "fullName")
    private String fullName;

    @Column(name = "dateOfBirth")
    private String dateOfBirth;

    @Column(name = "gender")
    private String gender;

    @Column(name = "email")
    private String email;

    @Column(name = "phoneNumber")
    private long phoneNumber;
}
