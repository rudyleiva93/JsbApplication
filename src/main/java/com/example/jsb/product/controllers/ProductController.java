package com.example.jsb.product.controllers;

import com.example.jsb.product.model.PatchProductCommand;
import com.example.jsb.product.model.Product;
import com.example.jsb.product.model.ProductDTO;
import com.example.jsb.product.model.UpdateProductCommand;
import com.example.jsb.product.services.*;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/products")
public class ProductController {

    private final CreateProductService createProductService;

    private final DeleteProductService deleteProductService;

    private final UpdateProductService updateProductService;

    private final PatchProductService patchProductService;

    private final GetProductsService getProductsService;

    private final GetProductService getProductService;

    private final SearchProductService searchProductService;



    public ProductController(CreateProductService createProductService, DeleteProductService deleteProductService,
                             UpdateProductService updateProductService, GetProductsService getProductsService,
                             GetProductService getProductService, PatchProductService patchProductService,
                             SearchProductService searchProductService) {
        this.createProductService = createProductService;
        this.deleteProductService = deleteProductService;
        this.updateProductService = updateProductService;
        this.patchProductService = patchProductService;
        this.getProductsService = getProductsService;
        this.getProductService = getProductService;
        this.searchProductService = searchProductService;
    }


    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody Product product){
        return createProductService.execute(product);
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getProducts(){
        return getProductsService.execute(null);
    }

    // Mapping to get product by id
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Integer id){
        return getProductService.execute(id);
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductDTO>> searchByProductName(@RequestParam String name) {
        return searchProductService.execute(name);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Integer id, @RequestBody Product product){
        //pass in both id and product together
        return updateProductService.execute(new UpdateProductCommand(id, product));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProductDTO> patchProduct(@PathVariable Integer id, @RequestBody JsonNode patchBody){
        return patchProductService.execute(new PatchProductCommand(id, patchBody));
    }

    @DeleteMapping("/{id}") //id here must match id in line below
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer id){
        return deleteProductService.execute(id);
    }
}
