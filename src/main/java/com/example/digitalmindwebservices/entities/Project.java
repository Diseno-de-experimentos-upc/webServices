package com.example.digitalmindwebservices.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "projects")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Project implements Serializable {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @Column(name = "name", nullable = false, length = 50)
        private String name;
        @Column(name = "description", nullable = false, length = 250)
        private String description;
        @Column(name = "icon_url", nullable = false, length = 250)
        private String iconUrl;
        @Column(name = "project_url", nullable = false, length = 250)
        private String projectUrl;
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "digital_profile_id", nullable = false)
        @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
        private DigitalProfile digitalProfile;
}
