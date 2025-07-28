package com.example.jsb;

import com.example.jsb.exceptions.ProductNotFoundException;
import com.example.jsb.product.interfaces.ProductRepository;
import com.example.jsb.product.model.Product;
import com.example.jsb.product.model.ProductDTO;
import com.example.jsb.product.services.GetProductService;

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
import org.springframework.http.ResponseEntity;
import java.util.Optional;

public class GetProductServiceTest {
    
    // What to mock the response of -> need this dependecy to runt the test
    @Mock
    private ProductRepository productRepository;

    // The thing we are testing
    @InjectMocks
    private GetProductService getProductService;

    // Things we need to setup before each test runs
    @BeforeEach
    public void setup() {
        // Initialize the repository and service
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void givenProductExists_whenGetProduct_returnProductDto() {

        // Given
        Product product = new Product();
        product.setId(1);
        product.setName("Product Name");
        product.setDescription("Product Description which is at least 20 characters long");
        product.setPrice(9.99);

        // still part of setting up the test
        when(productRepository.findById(1)).thenReturn(Optional.of(product));

        // When
        ResponseEntity<ProductDTO> response = getProductService.execute(1);

        // Then
        assertEquals(ResponseEntity.ok(new ProductDTO(product)), response);
        //assets the product repository was only called once
        verify(productRepository, times(1)).findById(1);
    }

    @Test
    public void givenProductDoesNotExist_whenGetProduct_returnNotFound() {
        // Given
        when(productRepository.findById(1)).thenReturn(Optional.empty());

        // When & Then
        assertThrows(ProductNotFoundException.class, () -> getProductService.execute(1));
        verify(productRepository, times(1)).findById(1);
    }
}
