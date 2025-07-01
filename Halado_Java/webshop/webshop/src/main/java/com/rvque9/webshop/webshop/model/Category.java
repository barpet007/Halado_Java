package com.rvque9.webshop.webshop.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.Set;
import java.util.HashSet;

/**
 * Kategória modell osztály.
 * Egy terméknek több kategóriája is lehet, és egy kategória több termékhez is tartozhat.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name; // Kategória neve
    private String description; // Kategória leírása

    // Több-Több kapcsolat a Termékekkel.
    @ManyToMany(mappedBy = "categories")
    private Set<Product> products = new HashSet<>(); // Collection típus (Set<Product>)
}
