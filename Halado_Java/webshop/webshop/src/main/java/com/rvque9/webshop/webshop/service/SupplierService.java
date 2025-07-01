package com.rvque9.webshop.webshop.service;

import com.rvque9.webshop.webshop.dto.SupplierDTO;
import com.rvque9.webshop.webshop.exception.ResourceNotFoundException;
import com.rvque9.webshop.webshop.exception.DuplicateResourceException;
import com.rvque9.webshop.webshop.model.Product;
import com.rvque9.webshop.webshop.model.Supplier;
import com.rvque9.webshop.webshop.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Year;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Beszállítók üzleti logikáját kezelő szolgáltatás.
 */
@Service
public class SupplierService {

    private final SupplierRepository supplierRepository;

    @Autowired
    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    public List<SupplierDTO> getAllSuppliers() {
        return supplierRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public SupplierDTO getSupplierById(Long id) {
        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Beszállító nem található ezzel az ID-val: " + id));
        return convertToDto(supplier);
    }

    @Transactional
    public SupplierDTO createSupplier(SupplierDTO supplierDTO) {
        if (supplierRepository.findByNameIgnoreCase(supplierDTO.getName()).isPresent()) {
            throw new DuplicateResourceException("Beszállító ezzel a névvel '" + supplierDTO.getName() + "' már létezik.");
        }
        Supplier supplier = convertToEntity(supplierDTO);
        Supplier savedSupplier = supplierRepository.save(supplier);
        return convertToDto(savedSupplier);
    }

    @Transactional
    public SupplierDTO updateSupplier(Long id, SupplierDTO supplierDTO) {
        Supplier existingSupplier = supplierRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Beszállító nem található ezzel az ID-val: " + id));

        existingSupplier.setName(supplierDTO.getName());
        existingSupplier.setAddress(supplierDTO.getAddress());
        existingSupplier.setContactEmail(supplierDTO.getContactEmail());
        existingSupplier.setEstablishmentYear(supplierDTO.getEstablishmentYear());

        Supplier updatedSupplier = supplierRepository.save(existingSupplier);
        return convertToDto(updatedSupplier);
    }

    @Transactional
    public void deleteSupplier(Long id) {
        if (!supplierRepository.existsById(id)) {
            throw new ResourceNotFoundException("Beszállító nem található ezzel az ID-val: " + id);
        }
        supplierRepository.deleteById(id);
    }

    public List<SupplierDTO> searchSuppliers(String name, String address, Integer establishmentYear) {
        List<Supplier> suppliers;
        if (name != null && !name.isEmpty()) {
            suppliers = supplierRepository.findByNameContainingIgnoreCase(name);
        } else if (address != null && !address.isEmpty()) {
            suppliers = supplierRepository.findByAddressContainingIgnoreCase(address);
        } else if (establishmentYear != null) {
            suppliers = supplierRepository.findByEstablishmentYearBefore(Year.of(establishmentYear));
        } else {
            suppliers = supplierRepository.findAll();
        }
        return suppliers.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private SupplierDTO convertToDto(Supplier supplier) {
        SupplierDTO dto = new SupplierDTO();
        dto.setId(supplier.getId());
        dto.setName(supplier.getName());
        dto.setAddress(supplier.getAddress());
        dto.setContactEmail(supplier.getContactEmail());
        dto.setEstablishmentYear(supplier.getEstablishmentYear());
        if (supplier.getSuppliedProducts() != null) {
            dto.setSuppliedProductIds(supplier.getSuppliedProducts().stream().map(Product::getId).collect(Collectors.toList()));
        }
        return dto;
    }

    private Supplier convertToEntity(SupplierDTO supplierDTO) {
        Supplier supplier = new Supplier();
        if (supplierDTO.getId() != null) {
            supplier.setId(supplierDTO.getId());
        }
        supplier.setName(supplierDTO.getName());
        supplier.setAddress(supplierDTO.getAddress());
        supplier.setContactEmail(supplierDTO.getContactEmail());
        supplier.setEstablishmentYear(supplierDTO.getEstablishmentYear());
        return supplier;
    }
}
