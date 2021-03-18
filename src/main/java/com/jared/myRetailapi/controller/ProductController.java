package com.jared.myRetailapi.controller;

import com.jared.myRetailapi.model.Product;
import com.jared.myRetailapi.service.ProductService;
import com.jared.myRetailapi.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping(value="/{id}")
    public ResponseEntity<Product> getProductByProductId(@PathVariable("id") String id) {
        Product product = null;
        product = productService.findByProductId(id);

        return new ResponseEntity<Product>(product, HttpStatus.OK);
    }
}
