package com.itcentercrmquva.quvaitcentercrm.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "physical_stuff")
public class PhysicalStuff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "physical_face_id", referencedColumnName = "id")
    PhysicalFace physicalFace;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    StuffCategory stuffCategory;

    @Column(nullable = false)
    LocalDate startDate;

    @Column
    LocalDate endDate;

    @Column(name = "timestamp", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    public Timestamp timestamp;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Users user;


    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "org_id", referencedColumnName = "id")
    private Organizations organizations;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "physical_stuff_user_id",referencedColumnName = "id")
    private PhysicalStuffUser physicalStuffUser;

}