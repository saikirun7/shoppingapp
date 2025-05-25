package com.shoppingapp_next.customerservice_2.service;

import com.shoppingapp_next.customerservice_2.entity.Product;

import java.util.List;

public interface ProductService {
    Product addProduct(Product product);
    List<Product> getAllProducts();
}
