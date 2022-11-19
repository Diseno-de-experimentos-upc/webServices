package com.example.digitalmindwebservices.repository;

import com.example.digitalmindwebservices.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMessageRepository extends JpaRepository<Message, Long> {
    @Query(value = "SELECT DISTINCT ON (lastMessage.emitter_id)* FROM (SELECT * FROM messages m WHERE receiver_id =:userId ORDER BY id DESC) AS lastMessage", nativeQuery = true)
    List<Message> findLastMessageDeveloper(@Param("userId") long userId);

    @Query(value = "SELECT DISTINCT ON (lastMessage.receiver_id)* FROM (SELECT * FROM messages m WHERE emitter_id =:userId ORDER BY id DESC) AS lastMessage;", nativeQuery = true)
    List<Message> findLastMessageCompany(@Param("userId") long userId);
}
