package com.example.demoservice.repository.projection;


import java.math.BigDecimal;

public interface ProductWithUsername {
    String getName();
    String getDescription();
    BigDecimal getPrice();
    String getUsername();
}
