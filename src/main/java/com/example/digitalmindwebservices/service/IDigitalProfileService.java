package com.example.digitalmindwebservices.service;

import com.example.digitalmindwebservices.entities.DigitalProfile;

import java.util.List;
import java.util.Optional;

public interface IDigitalProfileService extends CrudService<DigitalProfile> {

    Optional<DigitalProfile> findDigitalProfileByDeveloperId(Long id) throws Exception;
}
