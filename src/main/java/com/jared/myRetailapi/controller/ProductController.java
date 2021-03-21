package com.jared.myRetailapi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jared.myRetailapi.exception.ResourceNotFoundException;
import com.jared.myRetailapi.model.Product;
import com.jared.myRetailapi.repository.RedSkyRepository;
import com.jared.myRetailapi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/**
 * @author jaredpowell
 */
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<Product> getProductByProductId(@PathVariable("id") String id) {
        Product product;
        try {
            product = productService.findByProductId(id);
        } catch (ResourceNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found", e);
        } catch (JsonProcessingException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found", e);
        }
        return new ResponseEntity<Product>(product, HttpStatus.OK);
    }

    @PutMapping(value="/{id}", produces = "application/json")
    public ResponseEntity<String> updateProductPrice(@PathVariable("id") String id, @RequestBody Product product) {
        productService.saveProductPrice(product);

        return new ResponseEntity<String>("saved", HttpStatus.OK);
    }
}
