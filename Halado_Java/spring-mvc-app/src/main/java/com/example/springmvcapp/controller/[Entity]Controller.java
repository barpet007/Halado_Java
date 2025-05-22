package com.example.springmvcapp.controller;

import com.example.springmvcapp.dto.[Entity]DTO;
import com.example.springmvcapp.model.[Entity];
import com.example.springmvcapp.service.[Entity]Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/[entity]")
public class [Entity]Controller {

    @Autowired
    private [Entity]Service [entity]Service;

    @PostMapping
    public ResponseEntity<[Entity]> createEntity(@RequestBody [Entity]DTO [entity]DTO) {
        [Entity] createdEntity = [entity]Service.createEntity([entity]DTO);
        return ResponseEntity.ok(createdEntity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<[Entity]> updateEntity(@PathVariable Long id, @RequestBody [Entity]DTO [entity]DTO) {
        [Entity] updatedEntity = [entity]Service.updateEntity(id, [entity]DTO);
        return ResponseEntity.ok(updatedEntity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEntity(@PathVariable Long id) {
        [entity]Service.deleteEntity(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<[Entity]>> getAllEntities(@RequestParam(required = false) String filter) {
        List<[Entity]> entities = [entity]Service.getAllEntities(filter);
        return ResponseEntity.ok(entities);
    }
}