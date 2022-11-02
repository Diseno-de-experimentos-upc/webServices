package com.example.digitalmindwebservices.controller;

import com.example.digitalmindwebservices.entities.Framework;
import com.example.digitalmindwebservices.service.impl.FrameworkServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.experimental.StandardException;
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

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = FrameworkController.class)
@ActiveProfiles("test")
public class FrameworkControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private FrameworkServiceImpl frameworkService;
    private List<Framework> frameworkList;

    @BeforeEach
    void setUp() {
        frameworkList = new ArrayList<>();
        frameworkList.add(new Framework(1L, "Spring Boot",
                "https://th.bing.com/th/id/OIP.sluuRP9RbH3MPqzbFNLEmQHaF_?pid=ImgDet&rs=1", "Spring Boot makes it easy to create stand-alone, production-grade Spring based Applications that you can \"just run\""));
        frameworkList.add(new Framework(2L, "Hibernate",
                "https://download.logo.wine/logo/Hibernate_(Java)/Hibernate_(Java)-Logo.wine.png", "Hibernate ORM is an object-relational mapping tool for the Java programming language"));
        frameworkList.add(new Framework(3L, "Angular",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/c/cf/Angular_full_color_logo.svg/1200px-Angular_full_color_logo.svg.png", "Angular is a TypeScript-based open-source web application framework led by the Angular Team at Google and by a community of individuals and corporations"));
        frameworkList.add(new Framework(4L, "React",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/a/a7/React-icon.svg/1200px-React-icon.svg.png", "React is an open-source, front end, JavaScript library for building user interfaces or UI components"));
    }

    @Test
    void getAllFrameworksTest() throws Exception {
        given(frameworkService.getAll()).willReturn(frameworkList);
        mockMvc.perform(get("/api/frameworks")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getFrameworkByIdTest() throws Exception {
        given(frameworkService.getById(1L)).willReturn(Optional.of(frameworkList.get(0)));
        mockMvc.perform(get("/api/v1/frameworks/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void insertFrameworkTest() throws Exception {
        Framework framework = new Framework(5L, "Laravel",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/9/9a/Laravel.svg/1200px-Laravel.svg.png", "Laravel is a free, open-source PHP web framework, created by Taylor Otwell and intended for the development of web applications following the model-view-controller (MVC) architectural pattern");
        given(frameworkService.save(framework)).willReturn(framework);
        mockMvc.perform(post("/api/v1/frameworks")
                .content(asJsonString(framework))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    void updateFrameworkTest() throws Exception {
        Long id = 1L;
        Framework framework = new Framework(1L, "Laravel",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/9/9a/Laravel.svg/1200px-Laravel.svg.png", "Laravel is a free, open-source PHP web framework, created by Taylor Otwell and intended for the development of web applications following the model-view-controller (MVC) architectural pattern");
        given(frameworkService.getById(id)).willReturn(Optional.of(framework));
        mockMvc.perform(put("/api/v1/frameworks/{id}", id)
                .content(asJsonString(framework))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteFrameworkTest() throws Exception {
        Long id = 1L;
        mockMvc.perform(delete("/api/v1/frameworks/{id}", id)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
