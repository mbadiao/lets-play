package com.example.letsplay.product.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ProductResponse {
    private String id;
    private String name;
    private String description;
    private Double price;
    private String userId;
}
