package com.example.digitalmindwebservices.controller;

import static org.junit.jupiter.api.Assertions.*;

import com.example.digitalmindwebservices.entities.Message;
import com.example.digitalmindwebservices.entities.User;
import com.example.digitalmindwebservices.service.impl.MessageServiceImpl;
import com.example.digitalmindwebservices.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = MessageController.class)
@ActiveProfiles("test")
public class MessageControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private MessageServiceImpl messageService;
    @MockBean
    private UserServiceImpl userService;
    private List<Message> messageList;

    @BeforeEach
    void setUp() {

        User emitter = new User (1L, "Juan", "Aguirre", "juan@gmail.com", "993293832",
                "juan123#", "Company", "I am a recruiter personal in amazon");
        User receiver = new User(2L, "Jose", "Perez", "perez14@gmail.com", "939863247",
                "jose123#", "developer", "I am a developer in java");
        User receiver2 = new User(3L, "Romeo", "Perez", "pythonloves@gmail.com", "967354127",
                "juan123#", "developer", "I am a developer in python");
        User emitter2 = new User (4L, "Luis", "Perez", "luis@gmail.com", "99329328326",
                "#A937917987", "company" ," I am a user of the system");

        messageList = new ArrayList<>();
        messageList.add(new Message(1L, "Hello, your profile looks good for my company" , emitter, receiver));
        messageList.add(new Message(1L, "Hello, your profile looks good for my company" , emitter, receiver2));
        messageList.add(new Message(1L, "Hello, your profile looks good for my company" , emitter2, receiver));
        messageList.add(new Message(1L, "Hello, your profile looks good for my company" , emitter2, receiver2));
    }

    @Test
    void findAllMessageTest() throws Exception {
        Long userId = 1L;
        given(messageService.getAll()).willReturn(messageList);
        mockMvc.perform(get("/api/v1/users/{userId}/messages", userId)).andExpect(status().isOk());
    }

    @Test
    void findUserByIdTest() throws Exception {
        Long userId = 1L;
        Long id = 1L;
        User emitter = new User (1L, "Juan", "Aguirre", "juan@gmail.com", "993293832",
                "juan123#", "Company", "I am a recruiter personal in amazon");
        User receiver = new User(2L, "Jose", "Perez", "perez14@gmail.com",
                "939863247", "jose123#", "developer", "I am a developer in java");
        Message message = new Message(1L, "Hello, you profile looks good for my company" , emitter, receiver);

        given(messageService.getById(id)).willReturn(Optional.of(message));
        mockMvc.perform(get("/api/v1/users/{userId}/messages/{id}", userId, id)).andExpect(status().isOk());
    }

    @Test
    void insertMessageTest() throws Exception {
        Long userId = 1L;
        User emitter = new User (1L, "Juan", "Aguirre", "juan@gmail.com", "993293832",
                "juan123#", "Company", "I am a recruiter personal in amazon");
        User receiver = new User(2L, "Jose", "Perez", "perez14@gmail.com",
                "939863247", "jose123#", "developer", "I am a developer in java");
        Message message = new Message(1L, "Hello, you profile looks good for my company" , emitter, receiver);

        mockMvc.perform(post("/api/v1/users/{userId}/messages", userId)
                        .content(asJsonString(message))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isCreated());
    }

    @Test
    void updateMessageTest() throws Exception {
        Long userId = 1L;
        Long id = 1L;
        User emitter = new User (1L, "Juan", "Aguirre", "juan@gmail.com", "993293832",
                "juan123#", "Company", "I am a recruiter personal in amazon");
        User receiver = new User(2L, "Jose", "Perez", "perez14@gmail.com",
                "939863247", "jose123#", "developer", "I am a developer in java");
        Message message = new Message(1L, "Hello, you profile looks good for my company" , emitter, receiver);

        given(messageService.getById(id)).willReturn(Optional.of(message));
        mockMvc.perform(put("/api/v1/users/{userId}/messages/{id}", id)
                        .content(asJsonString(message))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    void deleteMessageTest() throws Exception {
        Long userId = 1L;
        Long id = 1L;
        User emitter = new User (1L, "Juan", "Aguirre", "juan@gmail.com", "993293832",
                "juan123#", "Company", "I am a recruiter personal in amazon");
        User receiver = new User(2L, "Jose", "Perez", "perez14@gmail.com",
                "939863247", "jose123#", "developer", "I am a developer in java");
        Message message = new Message(1L, "Hello, you profile looks good for my company" , emitter, receiver);

        given(messageService.getById(id)).willReturn(Optional.of(message));
        mockMvc.perform(delete("/api/v1/users/{userId}/messages/{id}", userId, id)).andExpect(status().isOk());
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
