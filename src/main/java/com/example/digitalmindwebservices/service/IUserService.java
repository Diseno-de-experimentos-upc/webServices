package com.example.digitalmindwebservices.service;

import com.example.digitalmindwebservices.entities.User;

import java.util.List;

public interface IUserService extends CrudService<User> {

    User findByEmail(String email) throws Exception;

    List<User> findByFirstName(String firstName) throws Exception;
}
