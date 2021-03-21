package com.jared.myRetailapi.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jared.myRetailapi.model.Product;

/**
 * @author jaredpowell
 */
public interface ProductService {

    Product findByProductId(String id) throws JsonProcessingException;
    Product saveProductPrice(Product product);

}
