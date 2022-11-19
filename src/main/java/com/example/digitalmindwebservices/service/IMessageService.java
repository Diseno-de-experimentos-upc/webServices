package com.example.digitalmindwebservices.service;

import com.example.digitalmindwebservices.entities.Message;

import java.util.List;

public interface IMessageService extends CrudService<Message>{
    List<Message> findLastMessageDeveloper(long id) throws Exception;
    List<Message> findLastMessageCompany(long id) throws Exception;
}
