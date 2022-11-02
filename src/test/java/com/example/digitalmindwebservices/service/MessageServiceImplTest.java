package com.example.digitalmindwebservices.service;

import com.example.digitalmindwebservices.entities.Message;
import com.example.digitalmindwebservices.entities.User;
import com.example.digitalmindwebservices.repository.IMessageRepository;
import com.example.digitalmindwebservices.service.impl.MessageServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class MessageServiceImplTest {

    @Mock
    IMessageRepository messageRepository;
    @InjectMocks
    MessageServiceImpl messageService;

    @Test
    void saveTest() {
        User emitter = new User (1L, "Juan", "Aguirre", "juan@gmail.com", "993293832",
                "juan123#", "Company", "I am a recruiter personal in amazon");
        User receiver = new User(2L, "Jose", "Perez", "perez14@gmail.com",
                "939863247", "jose123#", "developer", "I am a developer in java");
        Message message = new Message(1L, "Hello, you profile looks good for my company" , emitter, receiver);
        given(messageRepository.save(message)).willReturn(message);

        Message savedMessage = null;
        try {
            savedMessage = messageService.save(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertThat(savedMessage).isNotNull();
        assertEquals(message,savedMessage);
    }

    @Test
    void deleteTest() throws Exception {
        Long id = 1L;
        messageService.delete(id);
        verify(messageRepository, times(1)).deleteById(id);
    }

    @Test
    void getAllTest() throws Exception{
        User emitter = new User (1L, "Juan", "Aguirre", "juan@gmail.com", "993293832",
                "juan123#", "Company", "I am a recruiter personal in amazon");
        User receiver = new User(2L, "Jose", "Perez", "perez14@gmail.com", "939863247",
                "jose123#", "developer", "I am a developer in java");
        User receiver2 = new User(3L, "Romeo", "Perez", "pythonloves@gmail.com", "967354127",
                "juan123#", "developer", "I am a developer in python");

        List<Message> list = new ArrayList<>();
        list.add(new Message(1L, "Hello, your profile looks good for my company" , emitter, receiver));
        list.add(new Message(2L, "Hello, your profile looks good for my company" , emitter, receiver2));

        given(messageRepository.findAll()).willReturn(list);
        List<Message> listExpected = messageService.getAll();
        assertEquals(list, listExpected);
    }
    @Test
    void getByIdTest() throws Exception {
        Long id = 1L;
        User emitter = new User (1L, "Juan", "Aguirre", "juan@gmail.com", "993293832",
                "juan123#", "Company", "I am a recruiter personal in amazon");
        User receiver = new User(2L, "Jose", "Perez", "perez14@gmail.com",
                "939863247", "jose123#", "developer", "I am a developer in java");
        Message message = new Message(1L, "Hello, you profile looks good for my company" , emitter, receiver);
        given(messageRepository.findById(id)).willReturn(java.util.Optional.of(message));
        Optional<Message> messageExpected = messageService.getById(id);
        assertEquals(Optional.of(message), messageExpected);
    }
}