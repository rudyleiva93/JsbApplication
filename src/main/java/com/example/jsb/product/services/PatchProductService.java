package com.example.jsb.product.services;

import com.example.jsb.exceptions.ProductNotFoundException;
import com.example.jsb.product.interfaces.Command;
import com.example.jsb.product.interfaces.ProductRepository;
import com.example.jsb.product.model.PatchProductCommand;
import com.example.jsb.product.model.Product;
import com.example.jsb.product.model.ProductDTO;
import com.example.jsb.product.validators.ProductValidator;
import com.example.jsb.utils.JsonUtils;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PatchProductService implements Command<PatchProductCommand, ProductDTO> {

    private final ProductRepository productRepository;

    public PatchProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ResponseEntity<ProductDTO> execute(PatchProductCommand command){
        Optional<Product> productOptional = productRepository.findById(command.id());
        if(productOptional.isPresent()){
            Product product = productOptional.orElseThrow(ProductNotFoundException::new);
            JsonNode existingProduct = JsonUtils.convertToJson(product);
            JsonNode patched = JsonUtils.merge(existingProduct, command.patchNode());
            product = JsonUtils.convertFromJson(patched, Product.class);
            product.setId(command.id());
            ProductValidator.execute(product);
            productRepository.save(product);
            return  ResponseEntity.ok(new ProductDTO(product));
        }
        throw new ProductNotFoundException();
    }

}
