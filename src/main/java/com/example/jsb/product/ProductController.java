package com.example.jsb.product;

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
public class ProductController {

    public final CreateProductService createProductService;

    public final DeleteProductService deleteProductService;

    public final UpdateProductService updateProductService;

    public final PatchProductService patchProductService;

    public final GetProductsService getProductsService;

    public final GetProductService getProductService;

    public ProductController(CreateProductService createProductService, DeleteProductService deleteProductService,
                             UpdateProductService updateProductService, GetProductsService getProductsService,
                             GetProductService getProductService, PatchProductService patchProductService) {
        this.createProductService = createProductService;
        this.deleteProductService = deleteProductService;
        this.updateProductService = updateProductService;
        this.patchProductService = patchProductService;
        this.getProductsService = getProductsService;
        this.getProductService = getProductService;
    }


    @PostMapping("/product")
    public ResponseEntity<ProductDTO> createProduct(@RequestBody Product product){
        return createProductService.execute(product);
    }

    @GetMapping(value = "/products")
    public ResponseEntity<List<ProductDTO>> getProducts(){
        return getProductsService.execute(null);
    }

    // Mapping to get product by id
    @GetMapping(value = "/product/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Integer id){
        return getProductService.execute(id);
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Integer id, @RequestBody Product product){
        //pass in both id and product together
        return updateProductService.execute(new UpdateProductCommand(id, product));
    }

    @PatchMapping("/product/{id}")
    public ResponseEntity<ProductDTO> patchProduct(@PathVariable Integer id, @RequestBody JsonNode patchBody){
        return patchProductService.execute(new PatchProductCommand(id, patchBody));
    }

    @DeleteMapping("/product/{id}") //id here must match id in line below
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer id){
        return deleteProductService.execute(id);
    }
}
