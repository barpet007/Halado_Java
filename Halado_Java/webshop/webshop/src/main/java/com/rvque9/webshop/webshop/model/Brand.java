package com.rvque9.webshop.webshop.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;

/**
 * Márka modell osztály.
 * Tartalmazza a márka alapadatait és a hozzá tartozó termékek listáját.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name; // Márka neve
    private String countryOfOrigin; // Származási ország
    private LocalDate establishmentDate; // Alapítási dátum

    // Egy-Több kapcsolat a Termékekkel: egy márkának több terméke is lehet.
    @OneToMany(mappedBy = "brand", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Product> products = new ArrayList<>(); // Collection típus (List<Product>)
}
