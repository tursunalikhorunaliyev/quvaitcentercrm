package com.itcentercrmquva.quvaitcentercrm.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "students")
public class Students {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    private String firstname;

    @Column
    private String lastname;

    @Column
    private String middlename;

    @Column
    private String phone;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "teachers_sub_subjects_id", referencedColumnName = "id")
    private TeachersSubSubjects teachersSubSubjects;

    @Column(name = "timestamp", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    public Timestamp timestamp;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Users user;

    @ManyToOne(cascade =  CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "org_id", referencedColumnName = "id")
    private Organizations organizations;
}