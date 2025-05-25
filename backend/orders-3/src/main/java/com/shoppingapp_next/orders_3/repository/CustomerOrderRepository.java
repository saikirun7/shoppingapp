package com.shoppingapp_next.orders_3.repository;

import com.shoppingapp_next.orders_3.entity.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, Long> {
    Long countByCustomerId(Long customerId);
    List<CustomerOrder> findAllByCustomerId(Long customerId);

    Optional<CustomerOrder> findByCustomerIdAndProductId(Long customerId, Long productId);
}
