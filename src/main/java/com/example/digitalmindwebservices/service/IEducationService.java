package com.example.digitalmindwebservices.service;

import com.example.digitalmindwebservices.entities.Education;

import java.util.Optional;

public interface IEducationService extends CrudService<Education> {
    Optional<Education> findByDigitalProfileId(Long id) throws Exception;
}
