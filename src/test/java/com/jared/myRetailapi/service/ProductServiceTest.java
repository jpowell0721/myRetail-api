package com.jared.myRetailapi.service;

import com.jared.myRetailapi.model.Product;
import com.jared.myRetailapi.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static com.jared.myRetailapi.Helper.createMockProduct;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static com.jared.myRetailapi.Helper.TEST_ID;

@SpringBootTest
class ProductServiceTest {
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
    public void testFindById_IdExists() {
        product = createMockProduct();

        doReturn(Optional.of(product)).when(productRepository).findById(TEST_ID);

        Optional<Product> testProduct = productService.findById(TEST_ID);

        assertEquals(testProduct, Optional.of(product));
    }

    @Test
    public void testFindById_IdDoesNotExist() {
        doReturn(Optional.empty()).when(productRepository).findById(TEST_ID);

        Optional<Product> testProduct = productService.findById(TEST_ID);

        assertFalse(testProduct.isPresent());
    }

    @Test
    public void testSaveProductPrice() {
        product = createMockProduct();

        doReturn(product).when(productRepository).save(any());

        Product newProduct = productService.save(product);

        assertEquals(newProduct.getCurrentPrice(), product.getCurrentPrice());
    }
}