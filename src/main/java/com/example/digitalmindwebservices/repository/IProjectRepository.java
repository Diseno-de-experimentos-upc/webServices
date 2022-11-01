package com.example.digitalmindwebservices.repository;

import com.example.digitalmindwebservices.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProjectRepository extends JpaRepository<Project, Long> {
}
