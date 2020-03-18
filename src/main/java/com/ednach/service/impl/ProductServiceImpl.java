package com.ednach.service.impl;

import com.ednach.model.Product;
import com.ednach.model.User;
import com.ednach.repository.ProductRepository;
import com.ednach.service.ProductService;
import com.ednach.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Implementation of service interface for Product entity
 */
@Service
@Transactional
public class ProductServiceImpl implements ProductService {
    private UserService userService;
    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository, UserService userService) {
        this.productRepository = productRepository;
        this.userService = userService;
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException());
    }

    @Override
    public Product findByProductName(String productName) {
        return productRepository.findByProductName(productName);
    }


    @Override
    public Product save(Product product) {
        return saveAndFlush(product);
    }

    @Override
    public Product update(Product product) {
        final Long id = product.getId();
        findById(id);
        return saveAndFlush(product);
    }

    @Override
    public void delete(Product product) {
        final Long id = product.getId();
        deleteById(id);
        productRepository.delete(product);
    }

    @Override
    public void deleteById(Long id) {
        findById(id);
        productRepository.deleteById(id);
    }

    private Product saveAndFlush(Product product) {
        product.setUser(userService.findById(product.getUser().getId()));
        return productRepository.saveAndFlush(product);
    }
}
