package com.example.digitalmindwebservices.service;

import com.example.digitalmindwebservices.entities.User;
import com.example.digitalmindwebservices.repository.IUserRepository;
import com.example.digitalmindwebservices.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


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
                "993293832", "juan123#", "developer", "I am a developer in java");
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

}
