package com.example.digitalmindwebservices.entities;

import javax.persistence.Entity;


import javax.persistence.*;
import java.io.Serializable;

@Entity
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "firstname", nullable = false, length = 50)
    private String firstName;
    @Column(name = "lastname", nullable = false, length = 50)
    private String lastName;
    @Column (name = "email", nullable = false, length = 50)
    private String email;
    private String phone;
    private String password;
    private String role;
    private String description;
}
