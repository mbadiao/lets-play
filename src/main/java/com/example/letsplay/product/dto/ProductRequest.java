package com.example.letsplay.product.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ProductRequest {
    @NotBlank(message = "Product name is required")
    @Size(min=2, max=30)
    private String name;

    @NotBlank(message = "Description is required")
    @Size(min=2, max=100, message = "Description must be less than 255 characters")
    private String description;


    @NotNull(message = "Price is required")
    @Positive(message = "Price must be greater than zero")
    private Double price;

    @NotBlank(message = "User ID is required")
    private String userId;
}


