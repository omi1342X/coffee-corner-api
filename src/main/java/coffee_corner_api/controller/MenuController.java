package coffee_corner_api.controller;

import coffee_corner_api.dto.MenuItemRequest;
import coffee_corner_api.dto.MenuItemResponse;
import coffee_corner_api.entity.Category;
import coffee_corner_api.service.MenuItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menu")
@RequiredArgsConstructor
public class MenuController {

    private final MenuItemService menuItemService;

    @GetMapping
    public List<MenuItemResponse> getAll(
            @RequestParam(required = false) Category category,
            @RequestParam(required = false) Boolean veg) {
        return menuItemService.getAll(category, veg);
    }

    @GetMapping("/{id}")
    public MenuItemResponse getById(@PathVariable Long id) {
        return menuItemService.getById(id);
    }

    @PostMapping
    public ResponseEntity<MenuItemResponse> create(@Valid @RequestBody MenuItemRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(menuItemService.create(request));
    }

    @PutMapping("/{id}")
    public MenuItemResponse update(@PathVariable Long id, @Valid @RequestBody MenuItemRequest request) {
        return menuItemService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        menuItemService.delete(id);
        return ResponseEntity.noContent().build();
    }
}