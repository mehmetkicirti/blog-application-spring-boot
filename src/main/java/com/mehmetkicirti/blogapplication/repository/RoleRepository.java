package com.mehmetkicirti.blogapplication.repository;

import com.mehmetkicirti.blogapplication.entity.concrete.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, String> {
    Optional<Role> findByName(String roleName);
}
