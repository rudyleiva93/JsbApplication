package com.example.jsb.product.services;

import com.example.jsb.product.Command;
import com.example.jsb.product.ProductRepository;
import com.example.jsb.product.model.PatchProductCommand;
import com.example.jsb.product.model.Product;
import com.example.jsb.product.model.ProductDTO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PatchProductService implements Command<PatchProductCommand, ProductDTO> {

    private ProductRepository productRepository;

    public PatchProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ResponseEntity<ProductDTO> execute(PatchProductCommand command){
        Optional<Product> productOptional = productRepository.findById(command.getId());
        ObjectMapper objectMapper = new ObjectMapper();
        if(productOptional.isPresent()){
            JsonNode eixistingProduct = objectMapper.valueToTree(productOptional);
            JsonNode merged = merge(eixistingProduct, command.getPatchNode());
            //TODO: Convert JsonNode to Product object and return back ProductDTO as response for PATCH
            //Product product =
        }
    }

    private JsonNode merge(JsonNode original, JsonNode updates){
        updates.fieldNames().forEachRemaining(fieldName -> {
            JsonNode updatedValue = updates.get(fieldName);
            ((com.fasterxml.jackson.databind.node.ObjectNode) original).set(fieldName, updatedValue);
        });

        return original;
    }
}
