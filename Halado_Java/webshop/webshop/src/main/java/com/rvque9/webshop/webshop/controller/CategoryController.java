package com.rvque9.webshop.webshop.controller;

import com.rvque9.webshop.webshop.dto.CategoryDTO;
import com.rvque9.webshop.webshop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST kontroller a kategóriák kezeléséhez. Korábbi neve: GenreController.
 */
@RestController
@RequestMapping("/api/categories") // Az URL módosult
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    /**
     * Összes kategória lekérése, opcionális szűréssel.
     * GET /api/categories?name=Electronics
     * GET /api/categories?description=gadgets
     */
    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAllCategories(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String description) {
        if (name != null || description != null) {
            return ResponseEntity.ok(categoryService.searchCategories(name, description));
        }
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    /**
     * Egy kategória lekérése ID alapján.
     * GET /api/categories/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }

    /**
     * Új kategória létrehozása.
     * POST /api/categories
     */
    @PostMapping
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO categoryDTO) {
        CategoryDTO createdCategory = categoryService.createCategory(categoryDTO);
        return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);
    }

    /**
     * Meglévő kategória módosítása.
     * PUT /api/categories/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable Long id, @RequestBody CategoryDTO categoryDTO) {
        CategoryDTO updatedCategory = categoryService.updateCategory(id, categoryDTO);
        return ResponseEntity.ok(updatedCategory);
    }

    /**
     * Kategória törlése ID alapján.
     * DELETE /api/categories/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}
