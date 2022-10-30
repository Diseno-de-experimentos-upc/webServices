package com.example.digitalmindwebservices.service;

import com.example.digitalmindwebservices.entities.SocialNetwork;

import java.util.List;

public interface ISocialNetworkService extends CrudService<SocialNetwork> {
    List<SocialNetwork> findSocialNetworkByName(String name_social_network) throws Exception;
}
