package com.example.digitalmindwebservices.service.impl;

import com.example.digitalmindwebservices.entities.Notification;
import com.example.digitalmindwebservices.repository.INotificationRepository;
import com.example.digitalmindwebservices.service.INotificationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class NotificationServiceImpl implements INotificationService {

    private final INotificationRepository notificationRepository;

    public NotificationServiceImpl(INotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Override
    @Transactional
    public Notification save(Notification notification) throws Exception {
        return notificationRepository.save(notification);
    }

    @Override
    @Transactional
    public void delete(Long id) throws Exception {
        notificationRepository.deleteById(id);
    }

    @Override
    public List<Notification> getAll() throws Exception {
        return notificationRepository.findAll();
    }

    @Override
    public Optional<Notification> getById(Long id) throws Exception {
        return notificationRepository.findById(id);
    }
}
