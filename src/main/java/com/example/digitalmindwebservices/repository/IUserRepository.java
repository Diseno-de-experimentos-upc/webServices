package com.example.digitalmindwebservices.repository;

import com.example.digitalmindwebservices.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    List<User> findByFirstName(String firstName);

    List<User> findByRole(String role);

    @Query(value = "SELECT DISTINCT ON (u.id) u.*, 0 AS clazz_ FROM users u INNER JOIN developers d ON u.id = d.id INNER JOIN digital_profiles dp on d.id = dp.developer_id  FULL OUTER JOIN frameworks f ON dp.id = f.digital_profile_id  FULL OUTER JOIN programming_languages pl on dp.id = pl.digital_profile_id  FULL OUTER JOIN databases g ON dp.id = g.digital_profile_id  WHERE pl.name =:programmingLanguage AND f.name =:framework AND g.name =:database", nativeQuery = true)
    List<User> findDeveloperByFrameworkAndProgrammingLanguageAndDatabase(@Param("framework") String framework, @Param("programmingLanguage") String programmingLanguage, @Param("database") String database);
}
