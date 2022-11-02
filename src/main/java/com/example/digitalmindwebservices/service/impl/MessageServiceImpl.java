package com.example.digitalmindwebservices.service.impl;

import com.example.digitalmindwebservices.entities.Message;
import com.example.digitalmindwebservices.repository.IMessageRepository;
import com.example.digitalmindwebservices.service.IMessageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class MessageServiceImpl implements IMessageService {

    private final IMessageRepository messageRepository;

    public MessageServiceImpl(IMessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    @Transactional
    public Message save(Message message) throws Exception {
        return messageRepository.save(message);
    }

    @Override
    @Transactional
    public void delete(Long id) throws Exception {
        messageRepository.deleteById(id);
    }

    @Override
    public List<Message> getAll() throws Exception {
        return messageRepository.findAll();
    }

    @Override
    public Optional<Message> getById(Long id) throws Exception {
        return messageRepository.findById(id);
    }
}
