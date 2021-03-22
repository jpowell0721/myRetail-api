package com.jared.myRetailapi.service;

import com.jared.myRetailapi.model.Product;

import java.util.Optional;

/** @author jaredpowell */
public interface ProductService {
  Optional<Product> findById(String id);

  Product save(Product product);

  void updateProductPrice(String id, String value);
}
