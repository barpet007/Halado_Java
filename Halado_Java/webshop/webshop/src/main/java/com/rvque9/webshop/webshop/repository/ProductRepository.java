package com.rvque9.webshop.webshop.repository;

import com.rvque9.webshop.webshop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.Year;
import java.util.List;

/**
 * Product entitás adatbázis műveleteinek interfésze.
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByNameContainingIgnoreCase(String name);
    List<Product> findByReleaseYear(Year releaseYear);
    List<Product> findByPriceGreaterThan(BigDecimal price);
}
