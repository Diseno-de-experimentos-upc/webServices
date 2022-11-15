package com.example.digitalmindwebservices.service;

import com.example.digitalmindwebservices.entities.StudyCenter;

import java.util.List;

public interface IStudyCenterService extends CrudService<StudyCenter> {
    List<StudyCenter> findByEducationId(Long id);
}

