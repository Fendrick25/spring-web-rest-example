package com.example.demoservice.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Getter
@RequiredArgsConstructor
public class CreateProductRequest {

    @NotEmpty(message = "Name cannot be empty")
    @Size(min = 3, max = 15, message = "Name must be between 3 and 15 characters")
    private final String name;

    @Min(value = 0)
    private final BigDecimal price;

    @Min(value = 0)
    private final int quantity;

    @NotEmpty(message = "Description cannot be empty")
    @Size(min = 10, max = 3000, message = "Description must be between 10 and 3000 characters")
    private final String description;
}
