package com.example.digitalmindwebservices.controller;

import com.example.digitalmindwebservices.entities.User;
import com.example.digitalmindwebservices.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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

@WebMvcTest(controllers = UserController.class)
@ActiveProfiles("test")
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserServiceImpl userService;
    private List<User> userList;

//    @BeforeEach
//    void setUp() {
//        userList = new ArrayList<>();
//        userList.add(new User(1L, "Juan", "Perez", "juan@gmail.com", "993293832",
//                "juan123#", "developer", "I am a developer in java","...", "..."));
//        userList.add(new User(2L, "Luis", "Espiritu", "luis@gmail.com", "993293832",
//                "juan123#", "company", "I am a recruiter in google","...", "..."));
//        userList.add(new User(3L, "Romeo", "Perez", "juan@gmail.com", "993293832",
//                "juan123#", "developer", "I am a developer in java","...", "..."));
//        userList.add(new User(4L, "Juan", "Aguirre", "juan@gmail.com", "993293832",
//                "juan123#", "Company", "I am a recruiter personal in amazon","...", "..."));
//        userList.add(new User(5L, "Juan", "Perez", "juan@gmail.com", "993293832",
//                "juan123#", "developer", "I am a developer in java","...", "..."));
//    }
//
//    @Test
//    void findAllUserTest() throws Exception{
//        given(userService.getAll()).willReturn(userList);
//        mockMvc.perform(get("/api/users")).andExpect(status().isOk());
//    }
//
//    @Test
//    void findUserByIdTest() throws Exception {
//        Long id = 1L;
//        User user = new User(1L, "Juan",
//                "Perez", "juan@gmail.com", "993293832","987987987", "developer" ," I am a user of the system", "...", "...");
//        given(userService.getById(id)).willReturn(Optional.of(user));
//        mockMvc.perform(get("/api/users/{id}", id)).andExpect(status().isOk());
//    }
//
//    @Test
//    void findUserByEmail() throws Exception {
//        String email = "juan@gmail.com";
//        User user = new User(1L, "Juan",
//                "Perez", "juansito@gmail.com", "juan#123","987987987", "developer" ," I am a user of the system", "...", "...");
//        given(userService.findByEmail(email)).willReturn(user);
//        mockMvc.perform(get("/api/users/searchByEmail/{email}", email)).andExpect(status().isOk());
//    }
//
//    @Test
//    void findUserByFirstName() throws Exception {
//        String firstName = "Luis";
//        given(userService.findByFirstName(firstName)).willReturn(userList.stream()
//                .filter(u ->u.getFirstName().equals(firstName)).collect(Collectors.toList()));
//        mockMvc.perform(get("/api/users/searchByFirstName/{firstName}", firstName)).andExpect(status().isOk());
//    }
//    @Test
//    void insertUserTest() throws Exception {
//        User user = new User(1L, "Juan",
//                "Perez", "juan@gmail.com", "Juan#Az", "987987987", "developer" ," I am a user of the system, ", "...", "...");
//        mockMvc.perform(post("/api/users")
//                .content(asJsonString(user))
//                .contentType(MediaType.APPLICATION_JSON_VALUE)
//                .accept(MediaType.APPLICATION_JSON_VALUE))
//                .andExpect(status().isCreated());
//    }
//
//    @Test
//    void updateUserTest() throws Exception {
//        Long id = 1L;
//        User user = new User(1L, "Juan",
//                "Perez", "juanito@gmail.com", "Juan#Az", "987987987", "developer", " I am a user of the system", "...", "...");
//        given(userService.getById(id)).willReturn(Optional.of(user));
//        mockMvc.perform(put("/api/users/{id}", id)
//                .content(asJsonString(user))
//                .contentType(MediaType.APPLICATION_JSON_VALUE)
//                .accept(MediaType.APPLICATION_JSON_VALUE))
//                .andExpect(status().isOk());
//    }
//    @Test
//    void deleteUserTest() throws Exception {
//        Long id = 1L;
//        User user = new User(1L, "Juan",
//                "Perez", "juan@gmail.com", "Juan#Az", "987987987", "developer", " I am a user of the system", "...", "...");
//        given(userService.getById(id)).willReturn(Optional.of(user));
//        mockMvc.perform(delete("/api/users/{id}", id)).andExpect(status().isOk());
//    }
//    private static String asJsonString(final Object obj) {
//        try {
//            return new ObjectMapper().writeValueAsString(obj);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
}
