package coffee_corner_api.service;


import coffee_corner_api.dto.MenuItemRequest;
import coffee_corner_api.dto.MenuItemResponse;
import coffee_corner_api.entity.Category;
import coffee_corner_api.entity.MenuItem;
import coffee_corner_api.exception.ResourceNotFoundException;
import coffee_corner_api.repository.MenuItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuItemService {

    private final MenuItemRepository menuItemRepository;

    @Transactional(readOnly = true)
    public List<MenuItemResponse> getAll(Category category, Boolean veg) {
        List<MenuItem> items;
        if (category != null) {
            items = menuItemRepository.findByCategory(category);
        } else if (Boolean.TRUE.equals(veg)) {
            items = menuItemRepository.findByVegTrue();
        } else {
            items = menuItemRepository.findAll();
        }
        return items.stream().map(this::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public MenuItemResponse getById(Long id) {
        return toResponse(findOrThrow(id));
    }

    @Transactional
    public MenuItemResponse create(MenuItemRequest request) {
        MenuItem item = MenuItem.builder()
                .name(request.name())
                .description(request.description())
                .price(request.price())
                .category(request.category())
                .veg(request.veg())
                .available(request.available())
                .imageUrl(request.imageUrl())
                .build();
        return toResponse(menuItemRepository.save(item));
    }

    @Transactional
    public MenuItemResponse update(Long id, MenuItemRequest request) {
        MenuItem item = findOrThrow(id);
        item.setName(request.name());
        item.setDescription(request.description());
        item.setPrice(request.price());
        item.setCategory(request.category());
        item.setVeg(request.veg());
        item.setAvailable(request.available());
        item.setImageUrl(request.imageUrl());
        return toResponse(menuItemRepository.save(item));
    }

    @Transactional
    public void delete(Long id) {
        MenuItem item = findOrThrow(id);
        menuItemRepository.delete(item);
    }

    private MenuItem findOrThrow(Long id) {
        return menuItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Menu item not found with id " + id));
    }

    private MenuItemResponse toResponse(MenuItem item) {
        return new MenuItemResponse(
                item.getId(),
                item.getName(),
                item.getDescription(),
                item.getPrice(),
                item.getCategory(),
                item.isVeg(),
                item.isAvailable(),
                item.getImageUrl()
        );
    }
}