package com.example.springmvcapp.service;

import com.example.springmvcapp.exception.CustomException1;
import com.example.springmvcapp.exception.CustomException2;
import com.example.springmvcapp.model.[Entity];
import com.example.springmvcapp.repository.[Entity]Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class [Entity]Service {

    private final [Entity]Repository [entity]Repository;

    @Autowired
    public [Entity]Service([Entity]Repository [entity]Repository) {
        this.[entity]Repository = [entity]Repository;
    }

    public [Entity] create([Entity] [entity]) {
        return [entity]Repository.save([entity]);
    }

    public [Entity] update(Long id, [Entity] [entity]) throws CustomException1 {
        if (![entity]Repository.existsById(id)) {
            throw new CustomException1("Entity not found");
        }
        [entity].setId(id);
        return [entity]Repository.save([entity]);
    }

    public void delete(Long id) throws CustomException2 {
        if (![entity]Repository.existsById(id)) {
            throw new CustomException2("Entity not found");
        }
        [entity]Repository.deleteById(id);
    }

    public List<[Entity]> findAll() {
        return [entity]Repository.findAll();
    }

    public Optional<[Entity]> findById(Long id) {
        return [entity]Repository.findById(id);
    }

    public List<[Entity]> findBySomeCriteria(String criteria) {
        // Implement filtering logic based on criteria
        return [entity]Repository.findBySomeCriteria(criteria);
    }
}