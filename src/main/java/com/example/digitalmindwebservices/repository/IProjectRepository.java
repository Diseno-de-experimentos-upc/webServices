package com.example.digitalmindwebservices.repository;

import com.example.digitalmindwebservices.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProjectRepository extends JpaRepository<Project, Long> {

    List<Project> findByDigitalProfileId(Long id);

}
