package com.itcentercrmquva.quvaitcentercrm.repository;

import com.itcentercrmquva.quvaitcentercrm.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByUsername(String username);
    boolean existsByUsername(String username);

    List<Users> findByRoles_NameAndOrganization_Id(String name, Long id);
}