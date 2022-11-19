package com.example.digitalmindwebservices.service;

import com.example.digitalmindwebservices.entities.Database;

import java.util.List;

public interface IDatabaseService extends CrudService<Database> {

    List<Database> findByDigitalProfileId(Long id) throws Exception;
    List<Database> findByName(String name) throws Exception;
    List<Database> findByDeveloperId(Long id) throws Exception;
}
