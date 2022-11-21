package com.example.digitalmindwebservices.service;

import com.example.digitalmindwebservices.entities.User;
import com.example.digitalmindwebservices.repository.IUserRepository;
import com.example.digitalmindwebservices.service.impl.UserServiceImpl;
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
public class UserServiceImplTest {
    @Mock
    private IUserRepository userRepository;
    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void saveTest(){
        User user = new User(1L, "Juan", "Perez", "juan@gmail.com",
                "993293832", "juan123#", "developer", "I am a developer in java", "...", "...");
        given(userRepository.save(user)).willReturn(user);

        User savedUser = null;
        try {
            savedUser = userService.save(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertThat(savedUser).isNotNull();
        assertEquals(user, savedUser);
    }

    @Test
    public void deleteTest() throws Exception {
        Long id = 1L;
        userService.delete(id);
        verify(userRepository, times(1)).deleteById(id);
    }

    @Test
    public void getAllTest() throws Exception {
        List<User> list = new ArrayList<>();
        list.add(new User(1L, "Juan", "Perez", "juan@gmail.com", "993293832",
                "juan123#", "developer", "I am a developer in java","...", "..."));
        list.add(new User(2L, "Luis", "Espiritu", "luis@gmail.com", "993293832",
                "juan123#", "company", "I am a recruiter in google","...", "..."));
        list.add(new User(3L, "Romeo", "Perez", "juan@gmail.com", "993293832",
                "juan123#", "developer", "I am a developer in java","...", "..."));
        list.add(new User(4L, "Juan", "Aguirre", "juan@gmail.com", "993293832",
                "juan123#", "Company", "I am a recruiter personal in amazon","...", "..."));
        list.add(new User(5L, "Juan", "Perez", "juan@gmail.com", "993293832",
                "juan123#", "developer", "I am a developer in java","...", "..."));
        given(userRepository.findAll()).willReturn(list);
        List<User> listExpected = userService.getAll();
        assertEquals(list, listExpected);
    }

    @Test
    public void getByIdTest() throws Exception {
        Long id = 1L;
        User user = new User(1L, "Juan", "Perez", "juan@gmail.com", "993293832",
                "juan123#", "developer", "I am a developer in java","...", "...");
        given(userRepository.findById(id)).willReturn(java.util.Optional.of(user));
        Optional<User> userExpected = userService.getById(id);
        assertEquals(Optional.of(user), userExpected);
    }

    @Test
    public void findByEmailTest() throws Exception {
        String email = "juan@gmail.com";
        User user = new User(1L, "Juan", "Perez", "juan@gmail.com", "993293832",
                "juan123#", "developer", "I am a developer in java","...", "...");
        given(userRepository.findByEmail(email)).willReturn(user);
        User userExpected = userService.findByEmail(email);
        assertThat(userExpected).isNotNull();
        assertEquals(user, userExpected);
    }

    @Test
    public void findByFirstNameTest() throws Exception {
        String firstName = "Juan";
        List<User> list = new ArrayList<>();
        list.add(new User(1L, "Juan", "Perez", "juan@gmail.com", "993293832",
                "juan123#", "developer", "I am a developer in java","...", "..."));
        list.add(new User(2L, "Luis", "Espiritu", "luis@gmail.com", "993293832",
                "juan123#", "company", "I am a recruiter in google","...", "..."));
        list.add(new User(3L, "Romeo", "Perez", "juan@gmail.com", "993293832",
                "juan123#", "developer", "I am a developer in java","...", "..."));
        list.add(new User(4L, "Juan", "Aguirre", "juan@gmail.com", "993293832",
                "juan123#", "Company", "I am a recruiter personal in amazon","...", "..."));
        list.add(new User(5L, "Juan", "Perez", "juan@gmail.com", "993293832",
                "juan123#", "developer", "I am a developer in java","...", "..."));
        given(userRepository.findByFirstName(firstName)).willReturn(list);
        List<User> listExpected = userService.findByFirstName(firstName);
        assertEquals(list, listExpected);
    }
}
