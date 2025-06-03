package com.example.jsb.product.services;

import com.example.jsb.product.interfaces.Query;
import com.example.jsb.product.model.ProductDTO;
import com.example.jsb.product.interfaces.ProductRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchProductService implements Query<String, List<ProductDTO>> {
    private final ProductRepository productRepository;

    public SearchProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ResponseEntity<List<ProductDTO>> execute(String name) {
        return ResponseEntity.ok(productRepository.findByNameOrDescriptionContaining(name) // Can also use findByNameContaining method
                .stream()
                .map(ProductDTO::new)
                .toList());
    }
}
