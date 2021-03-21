package com.jared.myRetailapi.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

/** @author jaredpowell */
@Data
@Document(collection = "currentPrice")
public class CurrentPrice {

  private String value;
  private String currency_code;

  public CurrentPrice(String value, String currency_code) {
    this.value = value;
    this.currency_code = currency_code;
  }
}
