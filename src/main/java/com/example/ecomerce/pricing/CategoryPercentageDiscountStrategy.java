package com.example.ecomerce.pricing;

import com.example.ecomerce.model.Product;
import com.example.ecomerce.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
@Order(1)
public class CategoryPercentageDiscountStrategy implements DiscountStrategy {

    private final CategoryRepository categoryRepository;
    private final String categoryName;
    private final BigDecimal discountPercentage;

    public CategoryPercentageDiscountStrategy(
            CategoryRepository categoryRepository,
            @Value("${discounts.category-percentage.category-name}") String categoryName,
            @Value("${discounts.category-percentage.percentage}") BigDecimal discountPercentage) {
        if (categoryName.isBlank()) {
            throw new IllegalArgumentException("Discount category name must not be blank");
        }
        if (discountPercentage.signum() < 0
                || discountPercentage.compareTo(BigDecimal.valueOf(100)) > 0) {
            throw new IllegalArgumentException("Discount percentage must be between 0 and 100");
        }
        this.categoryRepository = categoryRepository;
        this.categoryName = categoryName;
        this.discountPercentage = discountPercentage;
    }

    @Override
    public boolean isApplicable(Product product) {
        return categoryRepository.findById(product.getCategory_id())
                .map(category -> category.getName().trim().equalsIgnoreCase(categoryName.trim()))
                .orElse(false);
    }

    @Override
    public BigDecimal getDiscountedPrice(Product product) {
        BigDecimal multiplier = BigDecimal.ONE.subtract(discountPercentage.movePointLeft(2));
        return BigDecimal.valueOf(product.getPrice())
                .multiply(multiplier)
                .setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public BigDecimal getDiscountPercentage() {
        return discountPercentage;
    }
}
