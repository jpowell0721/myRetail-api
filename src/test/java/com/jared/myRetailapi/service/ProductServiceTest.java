package com.jared.myRetailapi.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jared.myRetailapi.model.Product;
import com.jared.myRetailapi.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@SpringBootTest
class ProductServiceTest {

    private final String TEST_ID = "123";
    private final String TEST_NAME = "TestName";

    private Product product;

    @Autowired
    ProductService productService;

    @MockBean
    ProductRepository productRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindByProductId_IdExists() throws JsonProcessingException {
        product = createMockProduct();

        doReturn(product).when(productRepository).getProductById(TEST_ID);

        assertEquals(productService.findByProductId(TEST_ID), product);
    }

    @Test
    public void testFindByProductId_IdDoesNotExist() throws JsonProcessingException {
        product = createMockProduct();

        doReturn(product).when(productRepository).getProductById(TEST_ID);

        assertNotEquals(productService.findByProductId("incorrect_id"), product);
    }

    @Test
    public void testSaveProductPrice() throws JsonProcessingException {
        product = createMockProduct();

        doReturn(product).when(productRepository).save(any());

        Product newProduct = productService.saveProductPrice(product);

        assertEquals(newProduct.getCurrentPrice(), product.getCurrentPrice());
    }

    //helper method for creating a mock product
    private Product createMockProduct() {
        Map<String,String> currentPrice = new HashMap<>();
        currentPrice.put("value", "50");
        currentPrice.put("currency_code", "USD");
        Product product = new Product(TEST_ID, TEST_NAME, currentPrice);

        return product;
    }
}