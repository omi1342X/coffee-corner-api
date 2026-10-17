package coffee_corner_api.repository;


import coffee_corner_api.entity.Category;
import coffee_corner_api.entity.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
    List<MenuItem> findByCategory(Category category);
    List<MenuItem> findByVegTrue();
}