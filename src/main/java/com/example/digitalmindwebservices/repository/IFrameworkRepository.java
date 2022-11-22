package com.example.digitalmindwebservices.repository;

import com.example.digitalmindwebservices.entities.DigitalProfile;
import  com.example.digitalmindwebservices.entities.Framework;
import  org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IFrameworkRepository extends JpaRepository<Framework, Long> {

    List<Framework> findByDigitalProfileId(Long id);
    List<Framework> findFrameworksByName(String name);

    @Query(value = "SELECT f.* FROM frameworks f INNER JOIN digital_profiles dp ON f.digital_profile_id = dp.id INNER JOIN developers d ON dp.developer_id = d.id WHERE d.id =:id", nativeQuery = true)
    List<Framework> findFrameworksByUserId(Long id);
}
