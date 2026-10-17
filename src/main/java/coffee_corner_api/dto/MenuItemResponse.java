package coffee_corner_api.dto;


import coffee_corner_api.entity.Category;
import java.math.BigDecimal;

public record MenuItemResponse(
        Long id,
        String name,
        String description,
        BigDecimal price,
        Category category,
        boolean veg,
        boolean available,
        String imageUrl
) {}