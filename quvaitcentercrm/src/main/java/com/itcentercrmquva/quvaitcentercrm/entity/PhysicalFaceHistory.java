package com.itcentercrmquva.quvaitcentercrm.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "physical_face_history")
public class PhysicalFaceHistory {

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

    @Column(name = "photo", nullable = false, unique = true)
    private Long photo;

    @Column(name = "e_level", nullable = false)
    private Long educationLevel;

    @Column(name = "pyface_interests")
    private String interests;

    @Column(name = "timestamp", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    public Timestamp timestamp;

    @Column(name = "user_id", nullable = false)
    private Long user;

    // Additional
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "physical_face_id", referencedColumnName = "id", nullable = false)
    private PhysicalFace physicalFace;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "updater_user_id", referencedColumnName = "id", nullable = false)
    private Users updaterUser;

    @Column(name = "updated_time", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    public Timestamp updatedTime;

}
