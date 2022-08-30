package com.mehmetkicirti.blogapplication.repository;

import com.mehmetkicirti.blogapplication.entity.concrete.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    @Query("select u from User u where " +
            "u.email LIKE CONCAT('%',:usernameOrEmail,'%') or " +
            "u.username LIKE concat('%',:usernameOrEmail,'%')")
    Optional<User> findByUsernameOrEmail(String usernameOrEmail);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}
