package com.example.digitalmindwebservices.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;


import javax.persistence.*;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "firstname", nullable = false, length = 50)
    private String firstName;
    @Column(name = "lastname", nullable = false, length = 50)
    private String lastName;
    @Column (name = "email", nullable = false, length = 50)
    private String email;
    @Column(name = "phone", nullable = true, length = 20)
    private String phone;
    @Column (name = "password", nullable = false, length = 50)
    private String password;
    @Column (name = "role", nullable = false, length = 50)
    private String role;
    @Column (name = "description", nullable = true, length = 500)
    private String description;
}
