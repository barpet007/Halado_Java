package com.example.springmvcapp.repository;

import com.example.springmvcapp.model.[Entity];
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface [Entity]Repository extends JpaRepository<[Entity], Long> {
    
    List<[Entity]> findBySomeField(String someField);
    
    List<[Entity]> findByAnotherField(String anotherField);
    
    void deleteById(Long id);
}