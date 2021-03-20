package com.jared.myRetailapi.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jared.myRetailapi.model.Product;

import java.util.Optional;

public interface ProductService {
    Product findByProductId(String id) throws JsonProcessingException;
    public void saveProductPrice(Product product);

}
