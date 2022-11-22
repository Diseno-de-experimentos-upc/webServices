package com.example.digitalmindwebservices.repository;
import com.example.digitalmindwebservices.entities.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface INotificationRepository extends JpaRepository<Notification, Long>{
    @Query(value = "SELECT DISTINCT ON (lastNotification.emitter_id)* FROM (SELECT * FROM notifications n WHERE receiver_id =:userId ORDER BY id DESC) AS lastNotification", nativeQuery = true)
    List<Notification> findLastNotificationDeveloper(@Param("userId") long userId);

    @Query(value = "SELECT DISTINCT ON (lastNotification.receiver_id)* FROM (SELECT * FROM notifications n WHERE emitter_id =:userId ORDER BY id DESC) AS lastNotification;", nativeQuery = true)
    List<Notification> findLastNotificationCompany(@Param("userId") long userId);
}
