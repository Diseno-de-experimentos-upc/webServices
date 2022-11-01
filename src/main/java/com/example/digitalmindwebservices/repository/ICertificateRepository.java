package com.example.digitalmindwebservices.repository;

import com.example.digitalmindwebservices.entities.Certificate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICertificateRepository extends JpaRepository<Certificate, Long> {
}

