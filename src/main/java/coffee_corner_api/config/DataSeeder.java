package coffee_corner_api.config;


import coffee_corner_api.entity.Category;
import coffee_corner_api.entity.MenuItem;
import coffee_corner_api.repository.MenuItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {

    private final MenuItemRepository menuItemRepository;

    @Override
    public void run(String... args) {
        if (menuItemRepository.count() > 0) {
            return; // data already exists, don't insert again
        }

        menuItemRepository.saveAll(List.of(
                item("Masala Chai", "Brewed with ginger, cardamom and secret spices", "40", Category.TEA, true),
                item("Adrak Chai", "Strong ginger infused tea to refresh your soul", "35", Category.TEA, true),
                item("Irani Chai", "Hyderabadi style creamy, slow-boiled tea", "45", Category.TEA, true),
                item("Lemon Tea", "Light and refreshing black tea with lemon zest", "30", Category.TEA, true),

                item("Cappuccino", "Espresso with steamed milk and thick foam", "120", Category.COFFEE, true),
                item("Cold Coffee", "Chilled blended coffee with ice cream", "110", Category.COFFEE, true),

                item("Paneer Tikka", "Smoky grilled cottage cheese cubes", "180", Category.SNACKS, true),
                item("Chicken 65", "Spicy South Indian fried chicken bites", "200", Category.SNACKS, false),

                item("Butter Chicken", "Creamy tomato gravy with tandoori chunks", "350", Category.MAIN_COURSE, false),
                item("Chicken Biryani", "Long grain basmati rice cooked with aromatic spices", "320", Category.MAIN_COURSE, false),
                item("Paneer Butter Masala", "Cottage cheese in a rich buttery gravy", "280", Category.MAIN_COURSE, true),
                item("Dal Tadka", "Yellow lentils tempered with cumin and garlic", "150", Category.MAIN_COURSE, true),

                item("Mango Lassi", "Traditional yogurt based drink with mango pulp", "80", Category.BEVERAGES, true),
                item("Fresh Lime Soda", "Sweet or salty, your choice", "45", Category.BEVERAGES, true)
        ));
    }

    private MenuItem item(String name, String description, String price, Category category, boolean veg) {
        return MenuItem.builder()
                .name(name)
                .description(description)
                .price(new BigDecimal(price))
                .category(category)
                .veg(veg)
                .available(true)
                .build();
    }
}