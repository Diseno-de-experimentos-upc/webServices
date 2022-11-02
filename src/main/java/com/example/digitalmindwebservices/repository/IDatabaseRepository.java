package com.example.digitalmindwebservices.repository;

import com.example.digitalmindwebservices.entities.Database;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IDatabaseRepository extends JpaRepository<Database, Long> {
}
