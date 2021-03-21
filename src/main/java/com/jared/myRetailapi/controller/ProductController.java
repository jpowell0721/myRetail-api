package com.jared.myRetailapi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jared.myRetailapi.model.Product;
import com.jared.myRetailapi.service.ProductService;
import com.jared.myRetailapi.service.RedSkyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/** @author jaredpowell */
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

  private ProductService productService;
  private RedSkyService redSkyService;

  @Autowired
  public void setProductService(ProductService productService, RedSkyService redSkyService) {
    this.productService = productService;
    this.redSkyService = redSkyService;
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<?> getProductByProductId(@PathVariable("id") String id) {
    return productService
        .findById(id)
        .map(
            product -> {
              try {
                product.setName(redSkyService.findNameById(id));
                return new ResponseEntity<>(product, HttpStatus.OK);
              } catch (JsonProcessingException e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
              }
            })
        .orElse(ResponseEntity.notFound().build());
  }

  @PutMapping(value = "/{id}", produces = "application/json")
  public ResponseEntity<?> updateProductPrice(
      @PathVariable("id") String id, @RequestBody Product product) {
    Optional<Product> existingProduct = productService.findById(id);
    if (!existingProduct.isPresent()) {
      return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
    }

    productService.save(product);

    return new ResponseEntity<>(product, HttpStatus.OK);
  }
}
