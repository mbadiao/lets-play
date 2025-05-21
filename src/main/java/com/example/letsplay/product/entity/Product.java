package com.example.letsplay.product.entity;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document("products")
@Getter
@Setter
public class Product {
    @Id
    private String id;

    @Field
    private String name;

    @Field
    private String description;

    @Field
    private Double price;

    @Field
    private String userId;

}
