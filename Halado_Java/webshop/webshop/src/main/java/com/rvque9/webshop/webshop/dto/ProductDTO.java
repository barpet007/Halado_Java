package com.rvque9.webshop.webshop.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.time.Year;
import java.util.List;

/**
 * Termék adatátviteli objektum.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private Long id;
    private String name;
    private String description;
    private Year releaseYear;
    private BigDecimal price;
    private Integer stockQuantity;
    private Long brandId; // Márka ID-ja
    private String brandName; // Márka neve
    private Long supplierId; // Beszállító ID-ja
    private String supplierName; // Beszállító neve
    private List<Long> categoryIds; // Kategória ID-k listája
    private List<String> categoryNames; // Kategória nevek listája
}
