package com.jared.myRetailapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jared.myRetailapi.model.Product;
import com.jared.myRetailapi.service.ProductService;
import com.jared.myRetailapi.service.RedSkyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static com.jared.myRetailapi.Helper.TEST_CURRENCY_CODE;
import static com.jared.myRetailapi.Helper.TEST_ID;
import static com.jared.myRetailapi.Helper.TEST_NAME;
import static com.jared.myRetailapi.Helper.TEST_VALUE;
import static com.jared.myRetailapi.Helper.createMockProduct;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.is;

@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTest {
    private String URI_TEMPLATE = "/api/v1/products/{id}";

    private Product product;

    @MockBean
    private ProductService productService;

    @MockBean
    private RedSkyService redSkyService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetProductByProductId_IdFound() throws Exception {
        product = createMockProduct();

        doReturn(Optional.of(product)).when(productService).findById(TEST_ID);
        doReturn(TEST_NAME).when(redSkyService).findNameById(TEST_ID);

        mockMvc.perform(get(URI_TEMPLATE, TEST_ID))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(TEST_ID)))
                .andExpect(jsonPath("$.name", is(TEST_NAME)))
                .andExpect(jsonPath("$.currentPrice.value", is(TEST_VALUE)))
                .andExpect(jsonPath("$.currentPrice.currency_code", is(TEST_CURRENCY_CODE)));
    }

    @Test
    void testGetProductByProductId_IdNotFound() throws Exception {
        doReturn(Optional.empty()).when(productService).findById(TEST_ID);

        mockMvc.perform(get(URI_TEMPLATE, TEST_ID))
                .andExpect(status().isNotFound());
    }

    @Test
    void testUpdateProductPrice_IdFound() throws Exception {
        product = createMockProduct();

        Map<String, String> currency = new HashMap<>();
        currency.put("value", "500");
        currency.put("currency_code", TEST_CURRENCY_CODE);
        Product updatedProduct = new Product(TEST_ID, TEST_NAME, currency);

        doReturn(Optional.of(product)).when(productService).findById(TEST_ID);
        doReturn(updatedProduct).when(productService).save(any());

        mockMvc.perform(put("/api/v1/products/{id}", TEST_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedProduct)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(TEST_ID)))
                .andExpect(jsonPath("$.name", is(TEST_NAME)))
                .andExpect(jsonPath("$.currentPrice.value", is("500")))
                .andExpect(jsonPath("$.currentPrice.currency_code", is(TEST_CURRENCY_CODE)));
    }
}