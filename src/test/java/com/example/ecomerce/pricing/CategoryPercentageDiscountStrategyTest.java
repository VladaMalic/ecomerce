package com.example.ecomerce.pricing;

import com.example.ecomerce.model.Category;
import com.example.ecomerce.model.Product;
import com.example.ecomerce.repository.CategoryRepository;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CategoryPercentageDiscountStrategyTest {

    private final CategoryRepository categoryRepository = mock(CategoryRepository.class);
    private final CategoryPercentageDiscountStrategy strategy =
            new CategoryPercentageDiscountStrategy(categoryRepository, "Accesorii", BigDecimal.valueOf(15));

    @Test
    void appliesConfiguredDiscountToMatchingCategory() {
        Product product = product(12.35, 7L);
        Category category = new Category();
        category.setName("accesorii");
        when(categoryRepository.findById(7L)).thenReturn(Optional.of(category));

        assertTrue(strategy.isApplicable(product));
        assertEquals(new BigDecimal("10.50"), strategy.getDiscountedPrice(product));
        assertEquals(BigDecimal.valueOf(15), strategy.getDiscountPercentage());
    }

    @Test
    void doesNotApplyDiscountToAnotherCategory() {
        Product product = product(10, 8L);
        Category category = new Category();
        category.setName("Îmbrăcăminte");
        when(categoryRepository.findById(8L)).thenReturn(Optional.of(category));

        assertFalse(strategy.isApplicable(product));
    }

    private Product product(double price, long categoryId) {
        Product product = new Product();
        product.setPrice(price);
        product.setCategory_id(categoryId);
        return product;
    }
}
