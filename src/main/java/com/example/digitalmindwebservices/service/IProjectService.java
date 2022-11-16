package com.example.digitalmindwebservices.service;

import com.example.digitalmindwebservices.entities.Project;

import java.util.List;

public interface IProjectService extends CrudService<Project> {
    List<Project> findByDigitalProfileId(Long id) throws Exception;
}
