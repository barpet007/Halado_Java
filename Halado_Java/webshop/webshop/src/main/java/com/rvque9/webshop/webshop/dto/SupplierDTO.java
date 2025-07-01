package com.rvque9.webshop.webshop.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.Year;
import java.util.List;

/**
 * Beszállító adatátviteli objektum.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SupplierDTO {
    private Long id;
    private String name;
    private String address;
    private String contactEmail;
    private Year establishmentYear;
    private List<Long> suppliedProductIds; // Szállított termékek ID-i
}

