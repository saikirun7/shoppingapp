package com.shoppingapp_next.orders_3.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class User {
    @Id
    private Long customerId;
}
