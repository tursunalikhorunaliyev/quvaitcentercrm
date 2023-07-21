package com.itcentercrmquva.quvaitcentercrm.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "physical_face")
public class PhysicalFace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;

    @Column
    private String middleName;

    @Column(nullable = false)
    private LocalDate birthday;

    @Column(nullable = false, unique = true)
    private String personalIdentification;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false, length = 12)
    private String primaryPhone;

    @Column(length = 12)
    private String secondaryPhone;

    @Column
    private String telegramUsername;

    @Column
    private String instagramUsername;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "photo", referencedColumnName = "id", nullable = false, unique = true)
    private ImageStore photo;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "e_level", referencedColumnName = "id", nullable = false)
    private EducationLevel educationLevel;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "pyface_interests", joinColumns = @JoinColumn(name = "pyface_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "interest_id", referencedColumnName = "id"))
    private Set<Interests> interests;

    @Column(name = "timestamp", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    public Timestamp timestamp;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private Users user;










}