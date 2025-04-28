package com.example.jsb.product;

import com.example.jsb.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // Tells spring boot this is a repo for the database
public interface ProductRepository extends JpaRepository<Product, Integer> {
    // Just this alone, empty with no method signatures, gives us access to some free methods
}
