package com.example.jsb.product.services;

import com.example.jsb.exceptions.ProductNotFoundException;
import com.example.jsb.product.interfaces.Command;
import com.example.jsb.product.interfaces.ProductRepository;
import com.example.jsb.product.model.Product;
import com.example.jsb.product.model.ProductDTO;
import com.example.jsb.product.model.UpdateProductCommand;
import com.example.jsb.product.validators.ProductValidator;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateProductService implements Command<UpdateProductCommand, ProductDTO> {

    private final ProductRepository productRepository;

    public UpdateProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ResponseEntity<ProductDTO> execute(UpdateProductCommand command) {
        Optional<Product> productOptional = productRepository.findById(command.id());
        if(productOptional.isPresent()){
            Product product = command.product();
            product.setId(command.id());
            ProductValidator.execute(product);
            productRepository.save(product);
            return ResponseEntity.ok(new ProductDTO(product));
        }

        throw new ProductNotFoundException();
    }
}
