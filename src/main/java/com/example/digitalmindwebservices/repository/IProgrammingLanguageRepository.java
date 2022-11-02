package com.example.digitalmindwebservices.repository;

import com.example.digitalmindwebservices.entities.ProgrammingLanguage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProgrammingLanguageRepository extends JpaRepository<ProgrammingLanguage, Long> {
}
