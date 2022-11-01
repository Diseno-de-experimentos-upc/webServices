package com.example.digitalmindwebservices.repository;

import com.example.digitalmindwebservices.entities.StudyCenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IStudyCenterRepository extends JpaRepository<StudyCenter, Long> {

}
