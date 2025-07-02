package com.rvque9.webshop.webshop.controller;

import com.rvque9.webshop.webshop.dto.ProductDTO;
import com.rvque9.webshop.webshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * REST kontroller a termékek kezeléséhez. Korábbi neve: BookController.
 */
@RestController
@RequestMapping("/api/products") // Az URL módosult
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Összes termék lekérése, opcionális szűréssel.
     * GET /api/products?name=Laptop
     * GET /api/products?releaseYear=2023
     * GET /api/products?minPrice=1000.00
     * GET /api/products?categoryName=Electronics
     */
    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer releaseYear,
            @RequestParam(required = false) BigDecimal minPrice,
            @RequestParam(required = false) String categoryName) {
        if (name != null || releaseYear != null || minPrice != null || categoryName != null) {
            return ResponseEntity.ok(productService.searchProducts(name, releaseYear, minPrice, categoryName));
        }
        return ResponseEntity.ok(productService.getAllProducts());
    }

    /**
     * Egy termék lekérése ID alapján.
     * GET /api/products/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    /**
     * Új termék létrehozása.
     * POST /api/products
     */
    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO) {
        ProductDTO createdProduct = productService.createProduct(productDTO);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    /**
     * Meglévő termék módosítása.
     * PUT /api/products/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
        ProductDTO updatedProduct = productService.updateProduct(id, productDTO);
        return ResponseEntity.ok(updatedProduct);
    }

    /**
     * Termék törlése ID alapján.
     * DELETE /api/products/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
