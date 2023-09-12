package com.itcentercrmquva.quvaitcentercrm.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "teachers_sub_subjects")
public class TeachersSubSubjects {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "physical_stuff_id", referencedColumnName = "id", nullable = false)
    private PhysicalStuff physicalStuff;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "physical_face_ss", joinColumns = @JoinColumn(name = "physical_face_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "sub_subject_id", referencedColumnName = "id"))
    private Set<OrganizationsSubjectsWithSubSubjects> orgSubSubjects;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "org_id", referencedColumnName = "id", nullable = false)
    private Organizations organizations;

    @Column(name = "timestamp", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp timestamp;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private Users users;
}