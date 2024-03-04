package com.example.thuctap.repository;

import com.example.thuctap.entity.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RoleRepository extends JpaRepository<Role,Integer> {
    @Query("SELECT r FROM Role r WHERE r.name LIKE :x")
    Page<Role> searchRoleName(@Param("x") String s, Pageable pageable);

    Role findByName(String name);
}
