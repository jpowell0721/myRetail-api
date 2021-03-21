package com.jared.myRetailapi;

import com.jared.myRetailapi.model.Product;

import java.util.HashMap;
import java.util.Map;

public class Helper {
    public static final String TEST_ID = "123";
    public static final String TEST_NAME = "TestName";
    public static final String TEST_CURRENCY_CODE = "USD";
    public static final String TEST_VALUE = "50";

    public static Product createMockProduct() {
        Map<String,String> currentPrice = new HashMap<>();
        currentPrice.put("value", TEST_VALUE);
        currentPrice.put("currency_code", TEST_CURRENCY_CODE);
        Product product = new Product(TEST_ID, TEST_NAME, currentPrice);

        return product;
    }
}
