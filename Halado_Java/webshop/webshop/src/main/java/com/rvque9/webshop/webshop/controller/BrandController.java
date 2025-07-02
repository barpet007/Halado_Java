package com.rvque9.webshop.webshop.controller;

import com.rvque9.webshop.webshop.dto.BrandDTO;
import com.rvque9.webshop.webshop.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * REST kontroller a márkák kezeléséhez. Korábbi neve: AuthorController.
 */
@RestController
@RequestMapping("/api/brands") // Az URL módosult
public class BrandController {

    private final BrandService brandService;

    @Autowired
    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    /**
     * Összes márka lekérése, opcionális szűréssel.
     * GET /api/brands?name=Nike
     * GET /api/brands?countryOfOrigin=USA
     * GET /api/brands?minEstablishmentDate=1960-01-01
     */
    @GetMapping
    public ResponseEntity<List<BrandDTO>> getAllBrands(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String countryOfOrigin,
            @RequestParam(required = false) LocalDate minEstablishmentDate) {
        if (name != null || countryOfOrigin != null || minEstablishmentDate != null) {
            return ResponseEntity.ok(brandService.searchBrands(name, countryOfOrigin, minEstablishmentDate));
        }
        return ResponseEntity.ok(brandService.getAllBrands());
    }

    /**
     * Egy márka lekérése ID alapján.
     * GET /api/brands/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<BrandDTO> getBrandById(@PathVariable Long id) {
        return ResponseEntity.ok(brandService.getBrandById(id));
    }

    /**
     * Új márka létrehozása.
     * POST /api/brands
     */
    @PostMapping
    public ResponseEntity<BrandDTO> createBrand(@RequestBody BrandDTO brandDTO) {
        BrandDTO createdBrand = brandService.createBrand(brandDTO);
        return new ResponseEntity<>(createdBrand, HttpStatus.CREATED);
    }

    /**
     * Meglévő márka módosítása.
     * PUT /api/brands/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<BrandDTO> updateBrand(@PathVariable Long id, @RequestBody BrandDTO brandDTO) {
        BrandDTO updatedBrand = brandService.updateBrand(id, brandDTO);
        return ResponseEntity.ok(updatedBrand);
    }

    /**
     * Márka törlése ID alapján.
     * DELETE /api/brands/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBrand(@PathVariable Long id) {
        brandService.deleteBrand(id);
        return ResponseEntity.noContent().build();
    }
}