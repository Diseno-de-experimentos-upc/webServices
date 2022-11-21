package com.example.digitalmindwebservices.repository;

import com.example.digitalmindwebservices.entities.ProgrammingLanguage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProgrammingLanguageRepository extends JpaRepository<ProgrammingLanguage, Long> {

    List<ProgrammingLanguage> findByDigitalProfileId(Long id);
    List<ProgrammingLanguage> findProgrammingLanguagesByName(String name);
    @Query(value = "SELECT pl.* FROM programming_languages pl INNER JOIN digital_profiles dp ON pl.digital_profile_id = dp.id INNER JOIN developers d ON dp.developer_id = d.id WHERE d.id =:id", nativeQuery = true)
    List<ProgrammingLanguage> findProgrammingLanguagesByUserId(Long id);
}
