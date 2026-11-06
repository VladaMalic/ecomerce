package com.example.ecomerce.service;

import com.example.ecomerce.model.Product;
import com.example.ecomerce.pricing.DiscountStrategy;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DiscountServiceTest {

    @Test
    void usesFirstApplicableStrategy() {
        Product product = new Product();
        product.setPrice(100);
        DiscountStrategy firstStrategy = mock(DiscountStrategy.class);
        DiscountStrategy secondStrategy = mock(DiscountStrategy.class);
        when(firstStrategy.isApplicable(product)).thenReturn(true);
        when(firstStrategy.getDiscountedPrice(product)).thenReturn(BigDecimal.valueOf(85));
        when(firstStrategy.getDiscountPercentage()).thenReturn(BigDecimal.valueOf(15));

        DiscountService service = new DiscountService(List.of(firstStrategy, secondStrategy));

        assertEquals(
                new DiscountService.Discount(
                        BigDecimal.valueOf(100),
                        BigDecimal.valueOf(85),
                        BigDecimal.valueOf(15)),
                service.getDiscount(product));
    }
}
