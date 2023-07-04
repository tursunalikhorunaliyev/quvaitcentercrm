package com.itcentercrmquva.quvaitcentercrm.repository;

import com.itcentercrmquva.quvaitcentercrm.entity.ImageStore;
import com.itcentercrmquva.quvaitcentercrm.projection.projections.ImageStoreProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ImageStoreRepository extends JpaRepository<ImageStore, Long> {
    @Query("SELECT s FROM Subjects s")
    ImageStoreProjection getImageStoreData();
}