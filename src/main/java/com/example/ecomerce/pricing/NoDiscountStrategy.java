package com.example.ecomerce.pricing;

import com.example.ecomerce.model.Product;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@Order(Ordered.LOWEST_PRECEDENCE)
public class NoDiscountStrategy implements DiscountStrategy {

    @Override
    public boolean isApplicable(Product product) {
        return true;
    }

    @Override
    public BigDecimal getDiscountedPrice(Product product) {
        return BigDecimal.valueOf(product.getPrice());
    }

    @Override
    public BigDecimal getDiscountPercentage() {
        return BigDecimal.ZERO;
    }
}
