package com.rvque9.webshop.webshop.repository;

import com.rvque9.webshop.webshop.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Brand entitás adatbázis műveleteinek interfésze.
 */
@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {
    List<Brand> findByNameContainingIgnoreCase(String name); // Új metódus a részleges név szerinti szűréshez
    Optional<Brand> findByNameIgnoreCase(String name);
    List<Brand> findByEstablishmentDateAfter(LocalDate date);
    List<Brand> findByCountryOfOrigin(String countryOfOrigin);

    List<Brand> name(String name);
}
