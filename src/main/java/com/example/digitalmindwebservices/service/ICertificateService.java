package com.example.digitalmindwebservices.service;


import com.example.digitalmindwebservices.entities.Certificate;

import java.util.List;

public interface ICertificateService extends CrudService<Certificate> {
    List<Certificate> findByEducationId(Long id);
}


