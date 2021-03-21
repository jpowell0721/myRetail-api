package com.jared.myRetailapi.seeder;

import com.jared.myRetailapi.model.CurrentPrice;
import com.jared.myRetailapi.model.Product;
import com.jared.myRetailapi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author jaredpowell
 *     <p>Class for seeding data to MongoDB
 */
@Component
public class DatabaseSeeder {

  private ProductRepository productRepository;

  @Autowired
  public DatabaseSeeder(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  @EventListener
  public void seed(ContextRefreshedEvent event) {
    seedProductTable();
  }

  public void seedProductTable() {
    CurrentPrice currentPrice = new CurrentPrice("50", "USD");

    Product product = new Product("13860428", "", currentPrice);

    // purge previous data
    this.productRepository.deleteAll();

    this.productRepository.save(product);
  }
}
