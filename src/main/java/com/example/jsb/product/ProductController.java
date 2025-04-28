package com.example.jsb.product;

import com.example.jsb.product.model.Product;
import com.example.jsb.product.model.ProductDTO;
import com.example.jsb.product.model.UpdateProductCommand;
import com.example.jsb.product.services.CreateProductService;
import com.example.jsb.product.services.DeleteProductService;
import com.example.jsb.product.services.GetProductsService;
import com.example.jsb.product.services.GetProductService;
import com.example.jsb.product.services.UpdateProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    public final CreateProductService createProductService;

    public final DeleteProductService deleteProductService;

    public final UpdateProductService updateProductService;

    public final GetProductsService getProductsService;

    public final GetProductService getProductService;

    public ProductController(CreateProductService createProductService, DeleteProductService deleteProductService,
                             UpdateProductService updateProductService, GetProductsService getProductsService,
                             GetProductService getProductService) {
        this.createProductService = createProductService;
        this.deleteProductService = deleteProductService;
        this.updateProductService = updateProductService;
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

    /*Something with the @PostMapping below is wonky. It adds the data to teh db but in postman, we get a 406 status not acceptable error.
    We should be getting back a ProductDTO with the newly created product id*/
    @PutMapping("/product/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Integer id, @RequestBody Product product){
        //pass in both id and product together
        return updateProductService.execute(new UpdateProductCommand(id, product));
    }

    @DeleteMapping("/product/{id}") //id here must match id in line below
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer id){
        return deleteProductService.execute(id);
    }
}
