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
@Inheritance(strategy = InheritanceType.JOINED)
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
    @Column (name = "description", nullable = false, length = 500)
    private String description;
    @Column (name = "image", nullable = false, length = 500)
    private String image;
    @Column(name = "banner_image", nullable = false, length = 500)
    private String bannerImage;

    @Column(name = "rate", nullable = true)
    private Integer rate;

    public User(Long id, String firstName, String lastName, String email,
                String phone, String password, String role, String description,
                String image, String bannerImage) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.role = role;
        this.description = description;
        this.image = image;
        this.bannerImage = bannerImage;

    }

}
