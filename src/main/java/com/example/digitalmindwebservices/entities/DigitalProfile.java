package com.example.digitalmindwebservices.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "digital_profiles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DigitalProfile implements Serializable {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;
}
