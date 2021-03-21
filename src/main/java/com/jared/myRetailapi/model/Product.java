package com.jared.myRetailapi.model;

import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author jaredpowell
 *     <p>Utilize lombok in order to cut down on boilerplate code
 */
@Data
@Document(collection = "products")
public class Product {

  @Id @NonNull private String id;
  private String name;
  public CurrentPrice currentPrice;

  public Product(String id, String name, CurrentPrice currentPrice) {
    this.id = id;
    this.name = name;
    this.currentPrice = currentPrice;
  }
}
