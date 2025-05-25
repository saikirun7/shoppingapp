package com.shoppingapp_next.orders_3.controller;

import com.shoppingapp_next.orders_3.DTO.ProductDTO;
import com.shoppingapp_next.orders_3.entity.CustomerOrder;
import com.shoppingapp_next.orders_3.entity.Product;
import com.shoppingapp_next.orders_3.service.CustomerOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/customer-order")
@CrossOrigin(origins = "*")
public class CustomerOrderController {

    @Autowired
    private CustomerOrderService customerOrderService;

    @PostMapping("/add-order")
    public ResponseEntity<?> addCustomerOrder(@RequestBody CustomerOrder customerOrder){
        Map<String, Object> response = new HashMap<>();
        try{
            customerOrderService.addCustomerOrder(customerOrder);
            response.put("message", "Order Added Successfully");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e){
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/cart-count")
    public ResponseEntity<?> countCartOrders(@RequestParam Long customerId){
        Map<String, Object> response = new HashMap<>();
        try{
            Long count = customerOrderService.countCartOrders(customerId);
            response.put("count", count);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e){
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/cart-orders")
    public ResponseEntity<?> customerCartOrders(@RequestParam Long customerId) {
        Map<String, Object> response = new HashMap<>();
        try {
            List<ProductDTO> products = customerOrderService.customerCartOrders(customerId);
            response.put("products", products);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete-item")
    public ResponseEntity<?> deleteCartItem(@RequestParam Long customerId, @RequestParam Long productId) {
        Map<String, Object> response = new HashMap<>();
        try {
            customerOrderService.deleteCartItem(customerId, productId);
            response.put("message", "Item deleted successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

}
