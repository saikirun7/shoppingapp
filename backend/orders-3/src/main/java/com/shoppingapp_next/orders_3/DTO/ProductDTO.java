package com.shoppingapp_next.orders_3.DTO;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

public class ProductDTO {
    private Long productId;
    private String name;
    private String description;
    private Double price;

    // Getters
    public Long getProductId() {
        return productId;
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public Double getPrice() {
        return price;
    }

    // Setters
    public void setProductId(Long productId) {
        this.productId = productId;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
}


