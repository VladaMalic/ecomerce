package com.example.ecomerce.service;

import com.example.ecomerce.model.Product;
import com.example.ecomerce.pricing.DiscountStrategy;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class DiscountService {

    private final List<DiscountStrategy> strategies;

    public DiscountService(List<DiscountStrategy> strategies) {
        this.strategies = strategies;
    }

    public Discount getDiscount(Product product) {
        DiscountStrategy strategy = strategies.stream()
                .filter(candidate -> candidate.isApplicable(product))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("No discount strategy is configured"));
        return new Discount(
                BigDecimal.valueOf(product.getPrice()),
                strategy.getDiscountedPrice(product),
                strategy.getDiscountPercentage());
    }

    public record Discount(
            BigDecimal originalPrice,
            BigDecimal price,
            BigDecimal discountPercentage) {
    }
}
