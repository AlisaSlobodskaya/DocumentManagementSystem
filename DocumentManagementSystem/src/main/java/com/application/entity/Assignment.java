package com.application.entity;

import com.application.entity.enumeration.AssignmentStatus;
import com.application.entity.enumeration.EnumTypePostgreSql;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;

@Data
@Entity
@Table
@TypeDef(name = "enum_postgresql",
        typeClass = EnumTypePostgreSql.class)
public class Assignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    @Type(type = "enum_postgresql")
    private AssignmentStatus status;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "senderRoleId")
    private Role senderRole;

    @OneToOne
    @JoinColumn(name = "documentId")
    private Document document;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "recipientRoleId")
    private Role recipientRole;
}
