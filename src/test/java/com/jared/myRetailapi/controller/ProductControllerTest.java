package com.jared.myRetailapi.controller;

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
import org.springframework.test.web.servlet.RequestBuilder;

import java.util.Optional;

import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.is;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTest {
    @MockBean
    private ProductService productService;

    @MockBean
    private RedSkyService redSkyService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetProductByProductId_Found() throws Exception {
        Map<String,String> currentPrice = new HashMap<>();
        currentPrice.put("value", "50");
        currentPrice.put("currency_code", "USD");
        Product product = new Product("123", "test", currentPrice);

        doReturn(Optional.of(product)).when(productService).findById("123");
        doReturn("test").when(redSkyService).findNameById("123");

        RequestBuilder requestBuilder = null;
        mockMvc.perform(get("/api/v1/products/{id}", "123"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is("123")))
                .andExpect(jsonPath("$.name", is("test")))
                .andExpect(jsonPath("$.currentPrice.value", is("50")))
                .andExpect(jsonPath("$.currentPrice.currency_code", is("USD")));
    }

    @Test
    void testGetProductByProductId_NotFound() throws Exception {
        doReturn(Optional.empty()).when(productService).findById("123");

        mockMvc.perform(get("/api/v1/products/{id}", "123"))
                .andExpect(status().isNotFound());
    }
}