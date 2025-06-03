package com.example.jsb.product.interfaces;

import com.example.jsb.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Repository // Tells spring boot this is a repo for the database
public interface ProductRepository extends JpaRepository<Product, Integer> {

    // spring data jpa
    List<Product> findByNameContaining(String name);

    // JPQL
    @Query("SELECT p FROM Product p WHERE p.name LIKE %:keyword% OR p.description LIKE %:keyword%")
    List<Product> findByNameOrDescriptionContaining(@Param("keyword") String name);
}
