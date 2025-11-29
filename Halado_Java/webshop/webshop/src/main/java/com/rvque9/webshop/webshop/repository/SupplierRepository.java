package com.rvque9.webshop.webshop.repository;

import com.rvque9.webshop.webshop.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Year;
import java.util.List;
import java.util.Optional;

/**
 * Supplier entitás adatbázis műveleteinek interfésze.
 */
@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    List<Supplier> findByNameContainingIgnoreCase(String name);
    Optional<Supplier> findByNameIgnoreCase(String name);
    List<Supplier> findByEstablishmentYearBefore(Year year);
    List<Supplier> findByAddressContainingIgnoreCase(String address);
}
