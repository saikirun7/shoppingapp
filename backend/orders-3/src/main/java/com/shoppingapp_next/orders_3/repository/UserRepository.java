package com.shoppingapp_next.orders_3.repository;

import com.shoppingapp_next.orders_3.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
