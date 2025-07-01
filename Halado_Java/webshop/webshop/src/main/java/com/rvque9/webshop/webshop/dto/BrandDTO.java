package com.rvque9.webshop.webshop.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.util.List;

/**
 * Márka adatátviteli objektum.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BrandDTO {
    private Long id;
    private String name;
    private String countryOfOrigin;
    private LocalDate establishmentDate;
    private List<Long> productIds; // Termékek ID-i
}
