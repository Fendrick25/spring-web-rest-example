package com.example.demoservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
@AllArgsConstructor
public class ProductWithUsernameResponse {
    private String username;
    private String name;
    private String description;
    private BigDecimal price;
}
