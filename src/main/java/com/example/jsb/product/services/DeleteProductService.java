package com.example.jsb.product.services;

import com.example.jsb.product.Command;
import com.example.jsb.product.ProductRepository;
import com.example.jsb.product.model.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeleteProductService implements Command<Integer, Void> {

    private ProductRepository productRepository;

    public DeleteProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ResponseEntity<Void> execute(Integer id) {

      Optional<Product> productOptional = productRepository.findById(id);
      if(productOptional.isPresent()){
          productRepository.deleteById(id);
          return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
      }
      // add exception here later
      return null;
    }
}
