package com.jared.myRetailapi.seeder;

import com.jared.myRetailapi.model.Product;
import com.jared.myRetailapi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class DatabaseSeeder {
    private ProductRepository productRepository;

    @Autowired
    public DatabaseSeeder(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @EventListener
    public void seed(ContextRefreshedEvent event) {
        seedProductTable();
    }

    public void seedProductTable() {
        Map<String,String> currentPrice = new HashMap<>();
        currentPrice.put("value", "50");
        currentPrice.put("currency_code", "USD");

        Product product = new Product("13860428", "", currentPrice);


        //purge previous data
        this.productRepository.deleteAll();

        this.productRepository.save(product);
    }
}
