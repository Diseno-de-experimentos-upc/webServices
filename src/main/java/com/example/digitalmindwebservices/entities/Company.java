package com.example.digitalmindwebservices.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "companies")
@Data
@NoArgsConstructor
public class Company extends User {
    private String ruc;
    private String owner;

    public Company(Long id, String firstName, String lastName, String email, String phone, String password, String role, String description, String ruc, String owner) {
        super(id, firstName, lastName, email, phone, password, role, description);
        this.ruc = ruc;
        this.owner = owner;
    }
}
