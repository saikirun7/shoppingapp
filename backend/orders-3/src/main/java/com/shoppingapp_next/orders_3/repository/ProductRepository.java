package com.shoppingapp_next.orders_3.repository;

import com.shoppingapp_next.orders_3.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
