package com.example.digitalmindwebservices.entities;

import javax.persistence.*;
import java.io.Serializable;

public class Developer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


}
