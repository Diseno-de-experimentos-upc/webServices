package com.example.digitalmindwebservices.repository;

import  com.example.digitalmindwebservices.entities.Framework;
import  org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IFrameworkRepository extends JpaRepository<Framework, Long> {

    List<Framework> findByDigitalProfileId(Long id);

}
