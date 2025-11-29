package com.rvque9.webshop.webshop.service;

import com.rvque9.webshop.webshop.dto.ProductDTO;
import com.rvque9.webshop.webshop.exception.ResourceNotFoundException;
import com.rvque9.webshop.webshop.model.Category;
import com.rvque9.webshop.webshop.model.Product;
import com.rvque9.webshop.webshop.model.Supplier;
import com.rvque9.webshop.webshop.repository.BrandRepository;
import com.rvque9.webshop.webshop.repository.CategoryRepository;
import com.rvque9.webshop.webshop.repository.ProductRepository;
import com.rvque9.webshop.webshop.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Year;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * Termékek üzleti logikáját kezelő szolgáltatás. Korábbi neve: BookService.
 */
@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final BrandRepository brandRepository;
    private final SupplierRepository supplierRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, BrandRepository brandRepository,
                          SupplierRepository supplierRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.brandRepository = brandRepository;
        this.supplierRepository = supplierRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream()
                .map(this::convertToDto)
                .toList();
    }

    public ProductDTO getProductById(@NonNull Long id) {
        var product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Termék nem található ezzel az ID-val: " + id));
        return convertToDto(product);
    }

    @Transactional
    @SuppressWarnings("null")
    public ProductDTO createProduct(ProductDTO productDTO) {
        var product = convertToEntity(productDTO);
        Product savedProduct = Objects.requireNonNull(productRepository.save(product));
        return convertToDto(savedProduct);
    }

    @Transactional
    public ProductDTO updateProduct(@NonNull Long id, ProductDTO productDTO) {
        var existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Termék nem található ezzel az ID-val: " + id));

        existingProduct.setName(productDTO.getName());
        existingProduct.setDescription(productDTO.getDescription());
        existingProduct.setReleaseYear(productDTO.getReleaseYear());
        existingProduct.setPrice(productDTO.getPrice());
        existingProduct.setStockQuantity(productDTO.getStockQuantity());

        // Kapcsolatok frissítése
        if (productDTO.getBrandId() != null) {
            Long brandId = Objects.requireNonNull(productDTO.getBrandId());
            var brand = brandRepository.findById(brandId)
                    .orElseThrow(() -> new ResourceNotFoundException("Márka nem található ezzel az ID-val: " + brandId));
            existingProduct.setBrand(brand);
        } else {
            existingProduct.setBrand(null); // Kapcsolat bontása
        }

        if (productDTO.getSupplierId() != null) {
            Long supplierId = Objects.requireNonNull(productDTO.getSupplierId());
            Supplier supplier = supplierRepository.findById(supplierId)
                    .orElseThrow(() -> new ResourceNotFoundException("Beszállító nem található ezzel az ID-val: " + supplierId));
            existingProduct.setSupplier(supplier);
        } else {
            existingProduct.setSupplier(null); // Kapcsolat bontása
        }

        if (productDTO.getCategoryIds() != null && !productDTO.getCategoryIds().isEmpty()) {
            Set<Category> categories = new HashSet<>();
            for (Long categoryId : productDTO.getCategoryIds()) {
                Long catId = Objects.requireNonNull(categoryId, "Category ID cannot be null");
                var category = categoryRepository.findById(catId)
                    .orElseThrow(() -> new ResourceNotFoundException("Kategória nem található ezzel az ID-val: " + catId));
                categories.add(category);
            }
            existingProduct.setCategories(categories);
        } else {
            existingProduct.setCategories(new HashSet<>()); // Kategóriák törlése
        }

        var updatedProduct = productRepository.save(existingProduct);
        return convertToDto(updatedProduct);
    }

    @Transactional
    public void deleteProduct(@NonNull Long id) {
        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException("Termék nem található ezzel az ID-val: " + id);
        }
        productRepository.deleteById(id);
    }

    public List<ProductDTO> searchProducts(String name, Integer releaseYear, BigDecimal minPrice, String categoryName) {
        List<Product> products;
        if (name != null && !name.isEmpty()) {
            products = productRepository.findByNameContainingIgnoreCase(name);
        } else if (releaseYear != null) {
            products = productRepository.findByReleaseYear(Year.of(releaseYear));
        } else if (minPrice != null) {
            products = productRepository.findByPriceGreaterThan(minPrice);
        } else if (categoryName != null && !categoryName.isEmpty()) {
            // Ezt a lekérdezést közvetlenül a JpaRepository nem támogatja egyszerűen
            // Itt szükség lehet egy @Query annotációra vagy stream szűrésre
            products = productRepository.findAll().stream()
                    .filter(p -> p.getCategories().stream().anyMatch(c -> c.getName().equalsIgnoreCase(categoryName)))
                    .toList();
        }
        else {
            products = productRepository.findAll();
        }
        return products.stream()
                .map(this::convertToDto)
                .toList();
    }

    private ProductDTO convertToDto(Product product) {
        var dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setReleaseYear(product.getReleaseYear());
        dto.setPrice(product.getPrice());
        dto.setStockQuantity(product.getStockQuantity());
        if (product.getBrand() != null) {
            dto.setBrandId(product.getBrand().getId());
            dto.setBrandName(product.getBrand().getName());
        }
        if (product.getSupplier() != null) {
            dto.setSupplierId(product.getSupplier().getId());
            dto.setSupplierName(product.getSupplier().getName());
        }
        if (product.getCategories() != null) {
            dto.setCategoryIds(product.getCategories().stream().map(Category::getId).toList());
            dto.setCategoryNames(product.getCategories().stream().map(Category::getName).toList());
        }
        return dto;
    }

    private Product convertToEntity(ProductDTO productDTO) {
        var product = new Product();
        if (productDTO.getId() != null) {
            product.setId(productDTO.getId());
        }
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setReleaseYear(productDTO.getReleaseYear());
        product.setPrice(productDTO.getPrice());
        product.setStockQuantity(productDTO.getStockQuantity());

        if (productDTO.getBrandId() != null) {
            Long brandId = Objects.requireNonNull(productDTO.getBrandId());
            var brand = brandRepository.findById(brandId)
                    .orElseThrow(() -> new ResourceNotFoundException("Márka nem található ezzel az ID-val: " + brandId));
            product.setBrand(brand);
        }

        if (productDTO.getSupplierId() != null) {
            Long supplierId = Objects.requireNonNull(productDTO.getSupplierId());
            var supplier = supplierRepository.findById(supplierId)
                    .orElseThrow(() -> new ResourceNotFoundException("Beszállító nem található ezzel az ID-val: " + supplierId));
            product.setSupplier(supplier);
        }

        if (productDTO.getCategoryIds() != null && !productDTO.getCategoryIds().isEmpty()) {
            Set<Category> categories = new HashSet<>();
            for (Long categoryId : productDTO.getCategoryIds()) {
                Long catId = Objects.requireNonNull(categoryId, "Category ID cannot be null");
                var category = categoryRepository.findById(catId)
                        .orElseThrow(() -> new ResourceNotFoundException("Kategória nem található ezzel az ID-val: " + catId));
                categories.add(category);
            }
            product.setCategories(categories);
        }
        return product;
    }
}
