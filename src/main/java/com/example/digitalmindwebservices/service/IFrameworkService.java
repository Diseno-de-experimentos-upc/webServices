package com.example.digitalmindwebservices.service;

import com.example.digitalmindwebservices.entities.Framework;

import java.util.List;

public interface IFrameworkService extends CrudService<Framework> {
    List<Framework> findByDigitalProfileId(Long id);
}
