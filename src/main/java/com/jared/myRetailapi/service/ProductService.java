package com.jared.myRetailapi.service;

import com.jared.myRetailapi.model.Product;

public interface ProductService {

    Product findByProductId(String id);
}
