package com.example.digitalmindwebservices.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "social_network")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class SocialNetwork implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name_social_network", nullable = false, length = 50)
    private String nameSocialNetwork;
    @Column(name = "url_social_network", nullable = false, length = 100)
    private String urlSocialNetwork;
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private User user;
}
