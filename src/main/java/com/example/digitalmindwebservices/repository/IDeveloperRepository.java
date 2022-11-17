package com.example.digitalmindwebservices.repository;

import com.example.digitalmindwebservices.entities.Developer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDeveloperRepository extends JpaRepository<Developer, Long>{

}
