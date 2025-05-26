package com.example.jsb.product.services;

import com.example.jsb.product.interfaces.Command;
import com.example.jsb.product.interfaces.ProductRepository;
import com.example.jsb.product.model.PatchProductCommand;
import com.example.jsb.product.model.Product;
import com.example.jsb.product.model.ProductDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
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
        Optional<Product> productOptional = productRepository.findById(command.getId());
        if(productOptional.isPresent()){
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new Jdk8Module());

            JsonNode existingProduct = objectMapper.valueToTree(productOptional);
            JsonNode merged = merge(existingProduct, command.getPatchNode());

            // This looks ugly... will see if there is a better way of writing this bit.
            Product product = null;
            try {
                product = objectMapper.treeToValue(merged, Product.class);
            } catch (JsonProcessingException e) {
                // Will add 400 error handling later
                return null;
            }
            product.setId(command.getId());
            productRepository.save(product);
            return  ResponseEntity.ok(new ProductDTO(product));
        }
        return null;
    }

    private JsonNode merge(JsonNode original, JsonNode updates){
        updates.fieldNames().forEachRemaining(fieldName -> {
            JsonNode updatedValue = updates.get(fieldName);
            ((com.fasterxml.jackson.databind.node.ObjectNode) original).set(fieldName, updatedValue);
        });

        return original;
    }
}
