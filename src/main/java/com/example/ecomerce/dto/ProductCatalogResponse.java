package com.example.ecomerce.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

public record ProductCatalogResponse(
        Long id,
        String name,
        String description,
        BigDecimal price,
        BigDecimal originalPrice,
        BigDecimal discountPercentage,
        int stock,
        long category_id,
        Timestamp date) implements Serializable {
}
