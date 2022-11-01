package com.example.digitalmindwebservices.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "study_centers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudyCenter implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false, length = 50)
    private String name;
    @Column(name="icon_url", nullable = false, length = 250)
    private String iconUrl;
    @Column(name="progress", nullable = false)
    private Integer progress;
    @Column(name = "description", nullable = false, length = 250)
    private String description;
    @Column(name = "entry_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date entryDate;
    @Column(name = "graduation_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date graduationDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "education_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Education education;
}
