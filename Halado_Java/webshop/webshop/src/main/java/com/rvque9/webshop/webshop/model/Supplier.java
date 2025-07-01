package com.rvque9.webshop.webshop.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.ArrayList;
import java.time.Year;

/**
 * Beszállító modell osztály.
 * Tartalmazza a beszállító alapadatait és a szállított termékek listáját.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name; // Beszállító neve
    private String address; // Cím
    private String contactEmail; // Kapcsolattartó email
    private Year establishmentYear; // Alapítás éve

    // Egy-Több kapcsolat a Termékekkel: egy beszállító több terméket is szállíthat.
    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Product> suppliedProducts = new ArrayList<>(); // Collection típus (List<Product>)
}
