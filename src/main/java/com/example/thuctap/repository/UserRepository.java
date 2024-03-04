package com.example.thuctap.repository;

import com.example.thuctap.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User,Integer> {

    @Query("select u from User u where lower(u.name) like lower(:x)")
    Page<User> searchByName(@Param("x") String s, Pageable pageable);
    @Query("select u from User u where lower(u.name) like lower(:x) or lower(u.username) like lower(:x)")
    Page<User> searchByNameOrUsername(@Param("x") String s, Pageable pageable);

    User findByUsername(String username);
}
