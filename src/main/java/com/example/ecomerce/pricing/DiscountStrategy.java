package com.example.ecomerce.pricing;

import com.example.ecomerce.model.Product;

import java.math.BigDecimal;

public interface DiscountStrategy {

    boolean isApplicable(Product product);

    BigDecimal getDiscountedPrice(Product product);

    BigDecimal getDiscountPercentage();
}
