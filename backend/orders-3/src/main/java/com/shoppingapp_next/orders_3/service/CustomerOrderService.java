package com.shoppingapp_next.orders_3.service;

import com.shoppingapp_next.orders_3.DTO.ProductDTO;
import com.shoppingapp_next.orders_3.entity.CustomerOrder;
import com.shoppingapp_next.orders_3.entity.Product;
import com.shoppingapp_next.orders_3.exception.TryLaterException;

import java.util.List;

public interface CustomerOrderService {
    CustomerOrder addCustomerOrder(CustomerOrder customerOrder) throws TryLaterException;
    Long countCartOrders(Long customerId);
    List<ProductDTO> customerCartOrders(Long customerId);
    void deleteCartItem(Long customerId, Long productId);
}
