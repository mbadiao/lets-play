package com.example.letsplay.product.mapper;

import com.example.letsplay.product.dto.ProductRequest;
import com.example.letsplay.product.dto.ProductResponse;
import com.example.letsplay.product.entity.Product;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class ProductMapper {
    public Product toEntity(ProductRequest productRequest) {
           Product product = new Product();
           product.setName(productRequest.getName());
           product.setDescription(productRequest.getDescription());
           product.setPrice(productRequest.getPrice());
           product.setUserId(productRequest.getUserId());

           return product;
    }

    public ProductResponse toDtoResponse(Product product) {
        return new ProductResponse(product.getId(), product.getName(), product.getDescription(), product.getPrice(), product.getUserId());
    }

    public List<ProductResponse> toDtoResponseList(List<Product> products) {
        return products.stream()
                .map(this::toDtoResponse)
                .toList();
    }

}
