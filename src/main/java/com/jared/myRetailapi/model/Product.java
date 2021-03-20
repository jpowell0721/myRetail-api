package com.jared.myRetailapi.model;

import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

/**
 * @author jaredpowell
 *
 * Utilize lombok in order to cut down on boilerplate code
 */
@Data
@Document(collection="products")
public class Product {

    @Id
    @NonNull
    private String id;
    private String name;
    public Map<String,String> currentPrice;

    public Product(String id, String name, Map<String,String> currentPrice) {
        this.id = id;
        this.name = name;
        this.currentPrice = currentPrice;
    }
}
