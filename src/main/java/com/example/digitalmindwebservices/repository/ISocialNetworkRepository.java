package com.example.digitalmindwebservices.repository;

import com.example.digitalmindwebservices.entities.SocialNetwork;
import com.example.digitalmindwebservices.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ISocialNetworkRepository extends JpaRepository<SocialNetwork, Long> {
    @Query("select s from SocialNetwork s where s.nameSocialNetwork=:name")
    List<SocialNetwork> findSocialNetworkByName(@Param("name") String name_social_network);


}
