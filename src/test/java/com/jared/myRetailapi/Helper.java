package com.jared.myRetailapi;

import com.jared.myRetailapi.model.CurrentPrice;
import com.jared.myRetailapi.model.Product;

import java.util.HashMap;
import java.util.Map;

public class Helper {
  public static final String TEST_ID = "123";
  public static final String TEST_NAME = "TestName";
  public static final String TEST_CURRENCY_CODE = "USD";
  public static final String TEST_VALUE = "50";
  public static final String UPDATED_TEST_VALUE = "500";

  public static Product createMockProduct() {
    CurrentPrice currentPrice = new CurrentPrice(TEST_VALUE, TEST_CURRENCY_CODE);

    Product product = new Product(TEST_ID, TEST_NAME, currentPrice);

    return product;
  }
}
