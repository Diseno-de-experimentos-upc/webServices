package com.example.digitalmindwebservices.repository;

import com.example.digitalmindwebservices.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMessageRepository extends JpaRepository<Message, Long> {
}
