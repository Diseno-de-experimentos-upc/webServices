package com.example.digitalmindwebservices.repository;

import com.example.digitalmindwebservices.entities.Developer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDeveloperRepository extends JpaRepository<Developer, Long> {
}
