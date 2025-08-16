package com.example.jsb;

import com.example.jsb.product.interfaces.ProductRepository;
import com.example.jsb.product.model.Product;
import com.example.jsb.product.model.ProductDTO;
import com.example.jsb.product.services.CreateProductService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.Optional;

public class CreateProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private CreateProductService createProductService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void givenValidProduct_whenCreateProduct_returnCreatedProduct() {

        // Given
        Product product = new Product();
        product.setId(1);
        product.setName("New Product");
        product.setDescription("This is a new product with a description longer than 20 characters.");
        product.setPrice(19.99);

        // Mock the repository to return the created product
        when(productRepository.save(product)).thenReturn(product);

        // When
        ResponseEntity<ProductDTO> response = createProductService.execute(product);

        // Then
        assertEquals(ResponseEntity.status(HttpStatus.CREATED).body(new ProductDTO(product)), response);
        verify(productRepository, times(1)).save(product);
    }
}
