package com.example.digitalmindwebservices.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "developers")
@Data
@NoArgsConstructor
public class Developer extends User {
    public Developer(Long id, String firstName, String lastName, String email,
                     String phone, String password, String role, String description,
                     String image, String bannerImage) {
        super(id, firstName, lastName, email, phone, password, role, description, image, bannerImage);
    }

    public Developer(Long id, String firstName, String lastName, String email,
                     String phone, String password, String role, String description,
                     String image, String bannerImage,Integer rate) {
        super(id, firstName, lastName, email, phone, password, role, description, image, bannerImage, rate);
    }
}
