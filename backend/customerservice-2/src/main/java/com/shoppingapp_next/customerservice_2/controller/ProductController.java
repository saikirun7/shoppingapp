package com.shoppingapp_next.customerservice_2.controller;

import com.shoppingapp_next.customerservice_2.entity.Product;
import com.shoppingapp_next.customerservice_2.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "*")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("/addProduct")
    public ResponseEntity<?> addproduct(@RequestBody Product product){
        Map<String, Object> response = new HashMap<>();
        try{
            Product product1 = productService.addProduct(product);
            response.put("message", "Product added successfully");
            response.put("product", product1);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e){
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getProducts")
    public ResponseEntity<?> getProducts(){
        Map<String, Object> response = new HashMap<>();
        try{
            List<Product> products = productService.getAllProducts();
            response.put("products", products);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e){
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
