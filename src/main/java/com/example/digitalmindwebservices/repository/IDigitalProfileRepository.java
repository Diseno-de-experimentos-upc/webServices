package com.example.digitalmindwebservices.repository;

import com.example.digitalmindwebservices.entities.DigitalProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IDigitalProfileRepository extends JpaRepository<DigitalProfile,Long> {
    Optional<DigitalProfile> findDigitalProfileByDeveloperId(Long id);
}
