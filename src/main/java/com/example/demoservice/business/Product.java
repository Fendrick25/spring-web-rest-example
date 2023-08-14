package com.example.demoservice.business;

import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private String id;
    private String userId;
    private String name;
    private String description;
    private BigDecimal price;
    private int quantity;
    private BigDecimal totalValue;

    public void initialize(){
        id = UUID.randomUUID().toString();
    }

    public void calculateTotalValue(){
        totalValue = price.multiply(BigDecimal.valueOf(quantity));
    }
}
