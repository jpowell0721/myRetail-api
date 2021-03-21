package com.jared.myRetailapi.service.impl;

import com.jared.myRetailapi.model.Product;
import com.jared.myRetailapi.repository.ProductRepository;
import com.jared.myRetailapi.service.ProductService;
import org.springframework.stereotype.Service;

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
    public Product findByProductId(String id) {
        return productRepository.getProductById(id);
    }

    @Override
    public Product saveProductPrice(Product product) {
        return productRepository.save(product);
    }
}

