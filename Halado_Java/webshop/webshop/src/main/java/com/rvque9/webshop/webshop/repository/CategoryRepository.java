package com.rvque9.webshop.webshop.repository;

import com.rvque9.webshop.webshop.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Category entitás adatbázis műveleteinek interfésze.
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByNameContainingIgnoreCase(String name);
    Optional<Category> findByNameIgnoreCase(String name);
    List<Category> findByDescriptionContainingIgnoreCase(String description);
    List<Category> findAllByOrderByNameAsc();
}
