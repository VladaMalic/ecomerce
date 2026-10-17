package com.example.ecomerce.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.annotations.NotFound;
import org.springframework.validation.annotation.Validated;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @NotNull
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    @NotNull
    private double price;

    @Column(name = "stock")
    @NotNull
    private int stock = 0;

    @Column(name = "category_id ")
    private long category_id ;

    @Column(name = "created_at")
    @NotNull
    private Timestamp date = new Timestamp(System.currentTimeMillis());
}