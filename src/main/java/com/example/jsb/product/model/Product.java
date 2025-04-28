package com.example.jsb.product.model;

import jakarta.persistence.*;
import lombok.Data;

/*This class is what maps a java object to our sql database. It is basically a "model" object for the results it gets from the database table we specify.*/

@Entity //maps java class to mysql
@Data
@Table(name="product")
public class Product {

    @Id // all tables in mySQL need a primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto generates id from 1
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Double price;

    public String toString() {
        Integer var10000 = this.getId();
        return "Product(id=" + var10000 + ", name=" + this.getName() + ", description=" + this.getDescription() + ", price=" + this.getPrice() + ")";
    }
}
