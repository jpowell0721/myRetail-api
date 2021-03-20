package com.jared.myRetailapi.service.impl;

import com.jared.myRetailapi.model.Product;
import com.jared.myRetailapi.repository.ProductRepository;
import com.jared.myRetailapi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product findByProductId(String id) {
        return productRepository.getProductById(id);
    }

    @Override
    public void saveProductPrice(Product product) {
        productRepository.save(product);
    }
}

