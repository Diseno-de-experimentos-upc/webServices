package com.example.digitalmindwebservices.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "developer_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Developer developer;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "framework_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Framework framework;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "database_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Database database;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "programming_language_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private ProgrammingLanguage programmingLanguage;
}
