package com.example.demoservice.repository;

import com.example.demoservice.entity.ProductEntity;
import com.example.demoservice.repository.projection.ProductWithUsername;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductJpaRepository extends JpaRepository<ProductEntity, String> {

    @Query(value = "SELECT p.name as name, p.price as price, u.username as username, d.description as description " +
            "FROM products p " +
            "JOIN users u ON p.user_id = u.id " +
            "JOIN product_details d ON p.detail_id = d.id", nativeQuery = true)
    List<ProductWithUsername> findAllProductWithUsername();

}
