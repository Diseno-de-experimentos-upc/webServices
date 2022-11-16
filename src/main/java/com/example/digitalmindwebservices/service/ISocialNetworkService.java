package com.example.digitalmindwebservices.service;

import com.example.digitalmindwebservices.entities.SocialNetwork;
import com.example.digitalmindwebservices.entities.User;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ISocialNetworkService extends CrudService<SocialNetwork> {
    List<SocialNetwork> findSocialNetworkByName(String name_social_network) throws Exception;

    List<SocialNetwork> findByUserId(Long id) throws Exception;
}
