package com.example.digitalmindwebservices.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "certificates")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Certificate implements Serializable {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title", nullable = false, length = 50)
    private String title;
    @Column(name = "description", nullable = false, length = 250)
    private String description;
    @Column(name ="icon_url", nullable = false, length = 250)
    private String iconUrl;
    @Column(name = "obtained_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date obtainedDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "education_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Education education;

}
