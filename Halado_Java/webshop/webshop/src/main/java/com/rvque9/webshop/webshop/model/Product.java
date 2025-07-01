package com.rvque9.webshop.webshop.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.Year;
import java.util.Set;
import java.util.HashSet;
import java.math.BigDecimal;

/**
 * Termék modell osztály.
 * Tartalmazza a termék alapadatait és a hozzá tartozó márkát, beszállítót és kategóriákat.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name; // Termék neve
    private String description; // Termék leírása
    private Year releaseYear; // Kiadás/gyártás éve
    private BigDecimal price; // Ár
    private Integer stockQuantity; // Raktárkészlet

    // Sok-Egy kapcsolat a Márkával
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id")
    private Brand brand;

    // Sok-Egy kapcsolat a Beszállítóval
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    // Több-Több kapcsolat a Kategóriákkal
    @ManyToMany
    @JoinTable(
            name = "product_category", // Köztes tábla neve
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories = new HashSet<>(); // Collection típus (Set<Category>)
}
