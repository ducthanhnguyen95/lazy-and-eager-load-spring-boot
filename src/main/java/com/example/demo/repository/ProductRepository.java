package com.example.demo.repository;

import com.example.demo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT p FROM Product p JOIN p.category c WHERE LOWER(c.name) LIKE LOWER(CONCAT('%', :name, '%')) ORDER BY p.price DESC, p.name ASC")
    List<Product> findByCategoryNameLike(@Param("name") String name);
}
