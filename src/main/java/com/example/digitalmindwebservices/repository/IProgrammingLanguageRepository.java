package com.example.digitalmindwebservices.repository;

import com.example.digitalmindwebservices.entities.ProgrammingLanguage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProgrammingLanguageRepository extends JpaRepository<ProgrammingLanguage, Long> {

    List<ProgrammingLanguage> findByDigitalProfileId(Long id);
    List<ProgrammingLanguage> findProgrammingLanguagesByName(String name);
}
