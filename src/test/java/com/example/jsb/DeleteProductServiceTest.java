package com.example.jsb;

import com.example.jsb.exceptions.ProductNotFoundException;
import com.example.jsb.product.interfaces.ProductRepository;
import com.example.jsb.product.model.Product;
import com.example.jsb.product.services.DeleteProductService;

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
import org.springframework.http.HttpStatus;
import java.util.Optional;

public class DeleteProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private DeleteProductService deleteProductService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void givenProductExists_whenDeleteProduct_returnNoContent() {

        // Given
        Product product = new Product();
        product.setId(1);
        product.setName("Product Name");
        product.setDescription("Product Description which is at least 20 characters long");
        product.setPrice(9.99);

        when(productRepository.findById(1)).thenReturn(Optional.of(product));

        // When
        ResponseEntity<Void> response = deleteProductService.execute(1);

        // Then
        assertEquals(ResponseEntity.status(HttpStatus.NO_CONTENT).build(), response);
        verify(productRepository, times(1)).deleteById(1);
    }

    @Test
    public void givenProductDoesNotExist_whenDeleteProduct_throwProductNotFoundException() {
         //Given
         when(productRepository.findById(1)).thenReturn(Optional.empty());

         // When & Then
         assertThrows(ProductNotFoundException.class, () -> deleteProductService.execute(1));
         verify(productRepository, times(0)).deleteById(1);
    }
}
