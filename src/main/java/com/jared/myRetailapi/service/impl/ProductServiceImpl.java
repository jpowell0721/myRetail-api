package com.jared.myRetailapi.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jared.myRetailapi.model.Product;
import com.jared.myRetailapi.repository.ProductRepository;
import com.jared.myRetailapi.repository.RedSkyRepository;
import com.jared.myRetailapi.service.ProductService;
import org.springframework.stereotype.Service;

/**
 * @author jaredpowell
 */
@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    //We use RedSkyRepository to access redsky.target.com in order to extract name matching productId
    private RedSkyRepository redSkyRepository;

    public ProductServiceImpl(ProductRepository productRepository, RedSkyRepository redSkyRepository) {
        this.productRepository = productRepository;
        this.redSkyRepository = redSkyRepository;
    }

    @Override
    public Product findByProductId(String id) throws JsonProcessingException {
        Product product = productRepository.getProductById(id);
        product.setName(redSkyRepository.getProductTitleById(id));
        return product;
    }

    @Override
    public Product saveProductPrice(Product product) {
        return productRepository.save(product);
    }
}

