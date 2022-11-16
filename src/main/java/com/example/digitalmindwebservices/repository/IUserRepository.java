package com.example.digitalmindwebservices.repository;

import com.example.digitalmindwebservices.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    List<User> findByFirstName(String firstName);

    List<User> findByRole(String role);
}
