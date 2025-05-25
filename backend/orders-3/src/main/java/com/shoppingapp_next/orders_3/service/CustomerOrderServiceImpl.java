package com.shoppingapp_next.orders_3.service;

import com.shoppingapp_next.orders_3.DTO.ProductDTO;
import com.shoppingapp_next.orders_3.entity.Product;
import com.shoppingapp_next.orders_3.entity.User;
import com.shoppingapp_next.orders_3.entity.CustomerOrder;
import com.shoppingapp_next.orders_3.exception.TryLaterException;
import com.shoppingapp_next.orders_3.repository.CustomerOrderRepository;
import com.shoppingapp_next.orders_3.repository.ProductRepository;
import com.shoppingapp_next.orders_3.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerOrderServiceImpl implements CustomerOrderService {

    @Autowired
    private CustomerOrderRepository customerOrderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public CustomerOrder addCustomerOrder(CustomerOrder customerOrder) throws TryLaterException {
        Optional<User> findUser = userRepository.findById(customerOrder.getCustomerId());
        Optional<Product> findProduct = productRepository.findById(customerOrder.getProductId());

        if (findUser.isPresent() && findProduct.isPresent()) {
            return customerOrderRepository.save(customerOrder);
        }
        throw new TryLaterException("Try Later....");
    }

    @Override
    public Long countCartOrders(Long customerId) {
        return customerOrderRepository.countByCustomerId(customerId);
    }

    @Override
    public List<ProductDTO> customerCartOrders(Long customerId) {
        Optional<User> findUser = userRepository.findById(customerId);
        if (findUser.isEmpty()) {
            return Collections.emptyList();
        }

        // Fetch all customer orders for the given customer
        List<CustomerOrder> orders = customerOrderRepository.findAllByCustomerId(customerId);

        // Extract product IDs from the orders
        List<Long> productIds = orders.stream()
                .map(CustomerOrder::getProductId)
                .collect(Collectors.toList());

        // Fetch all products by their IDs
        List<Product> products = productRepository.findAllById(productIds);

        // Convert products to ProductDTO list
        List<ProductDTO> dtos = products.stream().map(p -> {
            ProductDTO dto = new ProductDTO();
            dto.setProductId(p.getProductId());
            dto.setName(p.getName());
            dto.setDescription(p.getDescription());
            dto.setPrice(p.getPrice());
            return dto;
        }).collect(Collectors.toList());

        return dtos;
    }

    @Override
    public void deleteCartItem(Long customerId, Long productId) {
        Optional<User> findUser = userRepository.findById(customerId);
        if (findUser.isPresent()) {
            Optional<CustomerOrder> orderOpt = customerOrderRepository.findByCustomerIdAndProductId(customerId, productId);
            if (orderOpt.isPresent()) {
                customerOrderRepository.delete(orderOpt.get());
            } else {
                throw new RuntimeException("Order not found for customerId " + customerId + " and productId " + productId);
            }
        } else {
            throw new RuntimeException("User not found with id " + customerId);
        }
    }

}
