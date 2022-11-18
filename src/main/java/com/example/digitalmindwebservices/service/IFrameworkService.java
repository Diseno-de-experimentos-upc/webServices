package com.example.digitalmindwebservices.service;

import com.example.digitalmindwebservices.entities.Developer;
import com.example.digitalmindwebservices.entities.Framework;

import java.awt.*;
import java.util.List;

public interface IFrameworkService extends CrudService<Framework> {
    List<Framework> findByDigitalProfileId(Long id) throws Exception;
    List<Framework> findByName(String name) throws Exception;
}
