package com.rvque9.webshop.webshop.controller;

import com.rvque9.webshop.webshop.dto.SupplierDTO;
import com.rvque9.webshop.webshop.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST kontroller a beszállítók kezeléséhez. Korábbi neve: PublisherController.
 */
@RestController
@RequestMapping("/api/suppliers") // Az URL módosult
public class SupplierController {

    private final SupplierService supplierService;

    @Autowired
    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    /**
     * Összes beszállító lekérése, opcionális szűréssel.
     * GET /api/suppliers?name=TechDistro
     * GET /api/suppliers?address=Budapest
     * GET /api/suppliers?establishmentYear=2000
     */
    @GetMapping
    public ResponseEntity<List<SupplierDTO>> getAllSuppliers(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String address,
            @RequestParam(required = false) Integer establishmentYear) {
        if (name != null || address != null || establishmentYear != null) {
            return ResponseEntity.ok(supplierService.searchSuppliers(name, address, establishmentYear));
        }
        return ResponseEntity.ok(supplierService.getAllSuppliers());
    }

    /**
     * Egy beszállító lekérése ID alapján.
     * GET /api/suppliers/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<SupplierDTO> getSupplierById(@PathVariable Long id) {
        return ResponseEntity.ok(supplierService.getSupplierById(id));
    }

    /**
     * Új beszállító létrehozása.
     * POST /api/suppliers
     */
    @PostMapping
    public ResponseEntity<SupplierDTO> createSupplier(@RequestBody SupplierDTO supplierDTO) {
        SupplierDTO createdSupplier = supplierService.createSupplier(supplierDTO);
        return new ResponseEntity<>(createdSupplier, HttpStatus.CREATED);
    }

    /**
     * Meglévő beszállító módosítása.
     * PUT /api/suppliers/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<SupplierDTO> updateSupplier(@PathVariable Long id, @RequestBody SupplierDTO supplierDTO) {
        SupplierDTO updatedSupplier = supplierService.updateSupplier(id, supplierDTO);
        return ResponseEntity.ok(updatedSupplier);
    }

    /**
     * Beszállító törlése ID alapján.
     * DELETE /api/suppliers/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSupplier(@PathVariable Long id) {
        supplierService.deleteSupplier(id);
        return ResponseEntity.noContent().build();
    }
}

