package com.itcentercrmquva.quvaitcentercrm.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "image_store")
public class ImageStore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "filename", nullable = false)
    private String filename;

    @Column(name = "content", nullable = false, columnDefinition = "MEDIUMBLOB NOT NULL")
    private byte[] content;

    @Column(name = "timestamp", nullable = false)
    @CreationTimestamp
    private Timestamp timestamp;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Users users;

}