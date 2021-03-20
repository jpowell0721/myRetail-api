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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import sun.security.provider.certpath.OCSPResponse;

import javax.swing.text.html.Option;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;
    private RedSkyRepository redSkyRepository;

    @Autowired
    public void setProductService(ProductService productService, RedSkyRepository redSkyRepository) {
        this.productService = productService;
        this.redSkyRepository = redSkyRepository;
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<Product> getProductByProductId(@PathVariable("id") String id) {
        Product product;
        try {
            product = productService.findByProductId(id);
            product.setName(redSkyRepository.getProductTitleById(id));
        } catch (ResourceNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Foundddd", e);
        } catch (JsonProcessingException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Foundddd", e);
        }
        return new ResponseEntity<Product>(product, HttpStatus.OK);
    }

    @PutMapping(value="/{id}", produces = "application/json")
    public ResponseEntity<String> updateProductPrice(@PathVariable("id") String id, @RequestBody Product product) {
        productService.saveProductPrice(product);

        return new ResponseEntity<String>("saved", HttpStatus.OK);
    }
}
