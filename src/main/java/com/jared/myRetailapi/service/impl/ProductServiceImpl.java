package com.jared.myRetailapi.service.impl;

import com.jared.myRetailapi.model.Product;
import com.jared.myRetailapi.repository.ProductRepository;
import com.jared.myRetailapi.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author jaredpowell
 */
@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Optional<Product> findById(String id){
        return productRepository.findById(id);
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }
}

