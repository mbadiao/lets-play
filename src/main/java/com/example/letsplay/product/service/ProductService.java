package com.example.letsplay.product.service;

import com.example.letsplay.exception.ResourceNotFoundException;
import com.example.letsplay.product.dto.ProductRequest;
import com.example.letsplay.product.dto.ProductResponse;
import com.example.letsplay.product.entity.Product;
import com.example.letsplay.product.mapper.ProductMapper;
import com.example.letsplay.product.repository.ProductRepository;
import com.example.letsplay.user.repository.UserRepository;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final UserRepository userRepository;

    @Transactional
    public ProductResponse createProduct(ProductRequest productRequest) {
         if (!userRepository.existsById(productRequest.getUserId())) {
             throw  new ResourceNotFoundException("User","User",productRequest.getUserId());
        }

         if (productRequest.getPrice() <= 0) {
             throw new ValidationException("Le prix doit être supérieur à zéro");
         }

        Product product = productMapper.toEntity(productRequest);
        Product productSaved = productRepository.save(product);
        return productMapper.toDtoResponse(productSaved);
    }

    @Transactional(readOnly = true)
    public List<ProductResponse> getProducts() {
        List<Product> product =  productRepository.findAll();
        return productMapper.toDtoResponseList(product);
    }

    @Transactional(readOnly = true)
    public ProductResponse getProductById(String id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product","id",id));
        return productMapper.toDtoResponse(product);
    }

    @Transactional
    public ProductResponse updateProduct(String id, ProductRequest request) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));

        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());

        Product updatedProduct = productRepository.save(product);
        return productMapper.toDtoResponse(updatedProduct);
    }

    @Transactional
    public void deleteProduct(String id) {
        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException("Produit", "id", id);
        }

        productRepository.deleteById(id);
    }
}
