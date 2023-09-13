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
    List<Users> findAllByRoles_NameAndOrganization_Id(String name, Long id);

    @Query("select u from Users u inner join u.roles roles where roles.name = ?1 and u.organization.id = ?2")
    Optional<Users> findByRoles_NameAndOrganization_Id(String name, Long id);
}