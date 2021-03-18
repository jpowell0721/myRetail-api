package com.jared.myRetailapi.repository;

import com.jared.myRetailapi.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {

    public Product getProductById(String productId);
}
