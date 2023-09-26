package com.application.entity;

import com.application.entity.enumeration.EnumTypePostgreSql;
import lombok.Data;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "users")
@TypeDef(name = "enum_postgresql",
        typeClass = EnumTypePostgreSql.class)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "username")
    @Size(min = 3, message = "Username must be at least 3 characters")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    @Size(min = 3, message = "Password must be at least 3 characters")
    private String password;

    @Transient
    private String passwordConfirm;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "roleId")
    private Role role;
}
