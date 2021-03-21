package com.jared.myRetailapi.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jared.myRetailapi.model.Product;
import com.jared.myRetailapi.repository.ProductRepository;
import com.jared.myRetailapi.repository.RedSkyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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

    @MockBean
    RedSkyRepository redSkyRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindById_IdExists() throws JsonProcessingException {
        product = createMockProduct();

        doReturn(Optional.of(product)).when(productRepository).findById(TEST_ID);

        Optional<Product> testProduct = productService.findById(TEST_ID);
        assertEquals(testProduct, Optional.of(product));
    }

    @Test
    public void testFindById_IdDoesNotExist() throws JsonProcessingException {
        doReturn(Optional.empty()).when(productRepository).findById(TEST_ID);
        Optional<Product> testProduct = productService.findById(TEST_ID);

        assertFalse(testProduct.isPresent());
    }

    @Test
    public void testSaveProductPrice() throws JsonProcessingException {
        product = createMockProduct();

        doReturn(product).when(productRepository).save(any());

        Product newProduct = productService.save(product);

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