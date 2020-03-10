package com.ednach.repository;

import com.ednach.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * ProductRepository provides the necessary search operations
 */
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT p.id , p.productName , p.cost FROM Product p JOIN p.producers pr")
    List<Product> findAll();

    Product findByProductName(String productName);

}
