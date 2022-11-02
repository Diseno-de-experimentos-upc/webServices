package com.example.digitalmindwebservices.service;

import com.example.digitalmindwebservices.entities.Framework;
import com.example.digitalmindwebservices.repository.IFrameworkRepository;
import com.example.digitalmindwebservices.service.impl.FrameworkServiceImpl;
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
public class FrameworkServiceImplTest {
    @Mock
    private IFrameworkRepository frameworkRepository;
    @InjectMocks
    private FrameworkServiceImpl frameworkService;

    @Test
    public void saveTest() {
        Framework framework = new Framework(1L, "Spring Boot","https://th.bing.com/th/id/OIP.WrM6x2H4ncsz9_dfVSghhQHaD4?pid=ImgDet&rs=1","Spring Boot makes it easy to create stand-alone, production-grade Spring based Applications that you can \"just run\".");
        given(frameworkRepository.save(framework)).willReturn(framework);

        Framework savedFramework = null;
        try {
            savedFramework = frameworkService.save(framework);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertThat(savedFramework).isNotNull();
        assertEquals(framework, savedFramework);
    }

    @Test
    public void deleteTest() throws Exception {
        Long id = 1L;
        frameworkService.delete(id);
        verify(frameworkRepository, times(1)).deleteById(id);
    }

    @Test
    public void getAllTest() throws Exception {
        List<Framework> list = new ArrayList<>();
        list.add(new Framework(1L, "Spring Boot","https://th.bing.com/th/id/OIP.WrM6x2H4ncsz9_dfVSghhQHaD4?pid=ImgDet&rs=1","Spring Boot makes it easy to create stand-alone, production-grade Spring based Applications that you can \"just run\"."));
        list.add(new Framework(2L, "Spring Data JPA","https://th.bing.com/th/id/OIP.WrM6x2H4ncsz9_dfVSghhQHaD4?pid=ImgDet&rs=1","Spring Data JPA makes it easy to easily implement JPA based repositories."));
        list.add(new Framework(3L, "Spring Data REST","https://th.bing.com/th/id/OIP.WrM6x2H4ncsz9_dfVSghhQHaD4?pid=ImgDet&rs=1","Spring Data REST allows you to expose your Spring Data repositories over REST with ease."));
        given(frameworkRepository.findAll()).willReturn(list);

        List<Framework> frameworkList = frameworkService.getAll();
        assertThat(frameworkList).isNotNull();
        assertEquals(list, frameworkList);
    }

    @Test
    public void getByIdTest() throws Exception {
        Long id = 1L;
        Framework framework = new Framework(1L, "Spring Boot","https://th.bing.com/th/id/OIP.WrM6x2H4ncsz9_dfVSghhQHaD4?pid=ImgDet&rs=1","Spring Boot makes it easy to create stand-alone, production-grade Spring based Applications that you can \"just run\".");
        given(frameworkRepository.findById(id)).willReturn(Optional.of(framework));
        Optional<Framework> frameworkExpected = frameworkService.getById(id);
        assertThat(frameworkExpected).isNotNull();
        assertEquals(Optional.of(framework), frameworkExpected);
    }
}
