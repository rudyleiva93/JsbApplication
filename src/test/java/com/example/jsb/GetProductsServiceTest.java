package com.example.jsb;

import com.example.jsb.product.interfaces.ProductRepository;
import com.example.jsb.product.model.Product;
import com.example.jsb.product.model.ProductDTO;
import com.example.jsb.product.services.GetProductsService;

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
import java.util.List;

public class GetProductsServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private GetProductsService getProductsService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void givenProductsExist_whenGetProducts_returnProductDtos() {

        // Given
        Product product1 = new Product();
        product1.setId(1);
        product1.setName("Product 1");
        product1.setDescription("Description 1 of Prodcut 1 that is at least 20 characters long");
        product1.setPrice(10.0);

        Product product2 = new Product();
        product2.setId(2);
        product2.setName("Product 2");
        product2.setDescription("Description 2 of Product 2 that is at least 20 characters long");
        product2.setPrice(20.0);

        List<Product> products = List.of(product1, product2);
        when(productRepository.findAll()).thenReturn(products);

        // When
        ResponseEntity<List<ProductDTO>> response = getProductsService.execute(null);

        // Then
        List<ProductDTO> expecteProductDTOs = products.stream()
                .map(ProductDTO::new)
                .toList();

        assertEquals(ResponseEntity.ok(expecteProductDTOs), response);
        verify(productRepository, times(1)).findAll();
    }
}
