package com.example.jsb.product.services;

import com.example.jsb.exceptions.ProductNotFoundException;
import com.example.jsb.product.interfaces.ProductRepository;
import com.example.jsb.product.interfaces.Query;
import com.example.jsb.product.model.Product;
import com.example.jsb.product.model.ProductDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetProductService implements Query<Integer, ProductDTO> {

    private final ProductRepository productRepository;

    public GetProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ResponseEntity<ProductDTO> execute(Integer input){
        Optional<Product> productOptional = productRepository.findById(input);
        if(productOptional.isPresent()){
            return ResponseEntity.ok(new ProductDTO((productOptional.get())));
        }

        throw new ProductNotFoundException();
    }
}
