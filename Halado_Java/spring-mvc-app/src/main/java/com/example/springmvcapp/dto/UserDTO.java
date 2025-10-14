package com.example.springmvcapp.dto;

public class UserDTO {
    private String name;
    private String email;
    
    // Default constructor
    public UserDTO() {}
    
    // Constructor
    public UserDTO(String name, String email) {
        this.name = name;
        this.email = email;
    }
    
    // Getters and setters
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
}