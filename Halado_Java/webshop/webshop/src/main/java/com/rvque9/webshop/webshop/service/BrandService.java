package com.rvque9.webshop.webshop.service;

import com.rvque9.webshop.webshop.dto.BrandDTO;
import com.rvque9.webshop.webshop.exception.ResourceNotFoundException;
import com.rvque9.webshop.webshop.exception.DuplicateResourceException;
import com.rvque9.webshop.webshop.model.Brand;
import com.rvque9.webshop.webshop.model.Product;
import com.rvque9.webshop.webshop.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Márkák üzleti logikáját kezelő szolgáltatás.
 */
@Service
public class BrandService {

    private final BrandRepository brandRepository;

    @Autowired
    public BrandService(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    public List<BrandDTO> getAllBrands() {
        return brandRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public BrandDTO getBrandById(Long id) {
        Brand brand = brandRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Márka nem található ezzel az ID-val: " + id));
        return convertToDto(brand);
    }

    @Transactional
    public BrandDTO createBrand(BrandDTO brandDTO) {
        if (brandRepository.findByNameIgnoreCase(brandDTO.getName()).isPresent()) {
            throw new DuplicateResourceException("Márka ezzel a névvel '" + brandDTO.getName() + "' már létezik.");
        }
        Brand brand = convertToEntity(brandDTO);
        Brand savedBrand = brandRepository.save(brand);
        return convertToDto(savedBrand);
    }

    @Transactional
    public BrandDTO updateBrand(Long id, BrandDTO brandDTO) {
        Brand existingBrand = brandRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Márka nem található ezzel az ID-val: " + id));

        existingBrand.setName(brandDTO.getName());
        existingBrand.setCountryOfOrigin(brandDTO.getCountryOfOrigin());
        existingBrand.setEstablishmentDate(brandDTO.getEstablishmentDate());

        Brand updatedBrand = brandRepository.save(existingBrand);
        return convertToDto(updatedBrand);
    }

    @Transactional
    public void deleteBrand(Long id) {
        if (!brandRepository.existsById(id)) {
            throw new ResourceNotFoundException("Márka nem található ezzel az ID-val: " + id);
        }
        brandRepository.deleteById(id);
    }

    public List<BrandDTO> searchBrands(String name, String countryOfOrigin, LocalDate minEstablishmentDate) {
        List<Brand> brands;
        if (name != null && !name.isEmpty()) {
            brands = brandRepository.findByNameContainingIgnoreCase(name);
        } else if (countryOfOrigin != null && !countryOfOrigin.isEmpty()) {
            brands = brandRepository.findByCountryOfOrigin(countryOfOrigin);
        } else if (minEstablishmentDate != null) {
            brands = brandRepository.findByEstablishmentDateAfter(minEstablishmentDate);
        } else {
            brands = brandRepository.findAll();
        }
        return brands.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private BrandDTO convertToDto(Brand brand) {
        BrandDTO dto = new BrandDTO();
        dto.setId(brand.getId());
        dto.setName(brand.getName());
        dto.setCountryOfOrigin(brand.getCountryOfOrigin());
        dto.setEstablishmentDate(brand.getEstablishmentDate());
        if (brand.getProducts() != null) {
            dto.setProductIds(brand.getProducts().stream().map(Product::getId).collect(Collectors.toList()));
        }
        return dto;
    }

    private Brand convertToEntity(BrandDTO brandDTO) {
        Brand brand = new Brand();
        if (brandDTO.getId() != null) {
            brand.setId(brandDTO.getId());
        }
        brand.setName(brandDTO.getName());
        brand.setCountryOfOrigin(brandDTO.getCountryOfOrigin());
        brand.setEstablishmentDate(brandDTO.getEstablishmentDate());
        return brand;
    }
}