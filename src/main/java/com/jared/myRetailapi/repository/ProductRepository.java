package com.jared.myRetailapi.repository;

import com.jared.myRetailapi.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/** @author jaredpowell */
@Repository
public interface ProductRepository extends MongoRepository<Product, String> {}
