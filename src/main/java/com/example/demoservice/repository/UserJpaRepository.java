package com.example.demoservice.repository;

import com.example.demoservice.entity.UserEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserJpaRepository extends JpaRepository<UserEntity, String> {
    @EntityGraph(attributePaths = {"products", "products.detail"})
    UserEntity findByUsername(String username);
}
