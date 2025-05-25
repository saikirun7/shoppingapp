package com.shoppingapp_next.customerservice_2.repository;

import com.shoppingapp_next.customerservice_2.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
