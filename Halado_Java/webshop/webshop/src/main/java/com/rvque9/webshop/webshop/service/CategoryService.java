package com.rvque9.webshop.webshop.service;

import com.rvque9.webshop.webshop.dto.CategoryDTO;
import com.rvque9.webshop.webshop.exception.ResourceNotFoundException;
import com.rvque9.webshop.webshop.exception.DuplicateResourceException;
import com.rvque9.webshop.webshop.model.Category;
import com.rvque9.webshop.webshop.model.Product;
import com.rvque9.webshop.webshop.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Kategóriák üzleti logikáját kezelő szolgáltatás. Korábbi neve: GenreService.
 */
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(this::convertToDto)
                .toList();
    }

    public CategoryDTO getCategoryById(@NonNull Long id) {
        var category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Kategória nem található ezzel az ID-val: " + id));
        return convertToDto(category);
    }

    @Transactional
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        if (categoryRepository.findByNameIgnoreCase(categoryDTO.getName()).isPresent()) {
            throw new DuplicateResourceException("Kategória ezzel a névvel '" + categoryDTO.getName() + "' már létezik.");
        }
        var category = convertToEntity(categoryDTO);
        var savedCategory = categoryRepository.save(category);
        return convertToDto(savedCategory);
    }

    @Transactional
    public CategoryDTO updateCategory(@NonNull Long id, CategoryDTO categoryDTO) {
        var existingCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Kategória nem található ezzel az ID-val: " + id));

        existingCategory.setName(categoryDTO.getName());
        existingCategory.setDescription(categoryDTO.getDescription());

        var updatedCategory = categoryRepository.save(existingCategory);
        return convertToDto(updatedCategory);
    }

    @Transactional
    public void deleteCategory(@NonNull Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new ResourceNotFoundException("Kategória nem található ezzel az ID-val: " + id);
        }
        categoryRepository.deleteById(id);
    }

    public List<CategoryDTO> searchCategories(String name, String description) {
        List<Category> categories;
        if (name != null && !name.isEmpty()) {
            categories = categoryRepository.findByDescriptionContainingIgnoreCase(name);
        } else if (description != null && !description.isEmpty()) {
            categories = categoryRepository.findByDescriptionContainingIgnoreCase(description);
        } else {
            categories = categoryRepository.findAllByOrderByNameAsc();
        }
        return categories.stream()
                .map(this::convertToDto)
                .toList();
    }

    private CategoryDTO convertToDto(Category category) {
        var dto = new CategoryDTO();
        dto.setId(category.getId());
        dto.setName(category.getName());
        dto.setDescription(category.getDescription());
        if (category.getProducts() != null) {
            dto.setProductIds(category.getProducts().stream().map(Product::getId).toList());
        }
        return dto;
    }

    private Category convertToEntity(CategoryDTO categoryDTO) {
        var category = new Category();
        if (categoryDTO.getId() != null) {
            category.setId(categoryDTO.getId());
        }
        category.setName(categoryDTO.getName());
        category.setDescription(categoryDTO.getDescription());
        return category;
    }
}
