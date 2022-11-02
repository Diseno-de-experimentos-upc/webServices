package com.example.digitalmindwebservices.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "programming_language")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class ProgrammingLanguage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "iconLink", nullable = false, length = 500)
    private String iconLink;

    @Column(name = "description", nullable = false, length = 500)
    private String description;
}
