package com.example.springmvcapp.dto;

public class EntityDTO {
    private Long id;
    private String name; // Example of a basic type
    private String description; // Example of a basic type
    private Integer quantity; // Example of a basic type
    private Double price; // Example of a basic type

    // Example of a collection type
    private List<AnotherEntityDTO> relatedEntities;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<AnotherEntityDTO> getRelatedEntities() {
        return relatedEntities;
    }

    public void setRelatedEntities(List<AnotherEntityDTO> relatedEntities) {
        this.relatedEntities = relatedEntities;
    }
}