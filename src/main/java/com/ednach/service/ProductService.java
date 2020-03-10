package com.ednach.service;

import com.ednach.model.Product;

import java.util.List;

/**
 * Interface with CRUD methods for Product entity
 */
public interface ProductService {
    List<Product> findAll();

    Product findById(Long id);

    Product findByProductName(String productName);

    Product save(Product product);

    Product update(Product product);

    void delete(Product product);

    void deleteById(Long id);

}
