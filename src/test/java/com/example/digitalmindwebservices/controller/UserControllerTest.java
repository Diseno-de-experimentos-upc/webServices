package com.example.digitalmindwebservices.controller;

import com.example.digitalmindwebservices.entities.User;
import com.example.digitalmindwebservices.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = UserController.class)
@ActiveProfiles("test")
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserServiceImpl userService;
    private List<User> userList;

    @BeforeEach
    void setUp() {
        userList = new ArrayList<>();
        userList.add(new User(1L, "Juan",
                "Perez", "juan@gmail.com", "9932932832",
                "#A987987987", "developer" ,"9932932833"));
        userList.add(new User(2L, "Maria", "Perez", "maria@gmail.com", "9932932834",
                "#A917987123", "company" ," I am a user of the system"));
        userList.add(new User(3L, "Pedro", "Cierto", "pedro@gmail.com", "9932932835",
                "#A927587187", "developer" ," I am a user of the system"));
        userList.add(new User(4L, "Luis", "Perez", "luis@gmail.com", "99329328326",
                "#A937917987", "company" ," I am a user of the system"));
        userList.add(new User(5L, "Ana", "Perez", "ana@gmail.com", "9932932837",
                "#A947987987", "developer" ," I am a user of the system"));
        userList.add(new User(6L, "Luisa", "Perez", "luisa@gmail.com", "9932932838",
                "#A957987987", "company" ," I am a user of the system"));
        userList.add(new User(7L, "Jose", "Perez", "jose@gmail.com", "9932932839",
                "#A967987987", "developer" ," I am a user of the system"));
        userList.add(new User(8L, "Luis", "Canales", "canales@gmail.com", "9932932830",
                "#A977987987", "company" ," I am a user of the system"));
        userList.add(new User(9L, "Luis", "Santos", "santos@gmail.com", "9932932832",
                "#A987987987", "developer" ," I am a user of the system"));
        userList.add(new User(10L, "Jorge", "Perez", "jorge@gmail.com", "Av. Monterrico 132",
                "#A997987987", "company" ," I am a user of the system"));
    }

    @Test
    void findAllUserTest() throws Exception{
        given(userService.getAll()).willReturn(userList);
        mockMvc.perform(get("/api/users")).andExpect(status().isOk());
    }

    @Test
    void findUserByIdTest() throws Exception {
        Long id = 1L;
        User user = new User(1L, "Juan",
                "Perez", "juan@gmail.com", "993293832","987987987", "developer" ," I am a user of the system");
        given(userService.getById(id)).willReturn(Optional.of(user));
        mockMvc.perform(get("/api/users/{id}", id)).andExpect(status().isOk());
    }
}
