package com.example.digitalmindwebservices.repository;
import com.example.digitalmindwebservices.entities.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface INotificationRepository extends JpaRepository<Notification, Long>{
}
