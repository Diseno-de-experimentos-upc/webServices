package com.example.digitalmindwebservices.controller;

import com.example.digitalmindwebservices.entities.DigitalProfile;
import com.example.digitalmindwebservices.entities.Project;
import com.example.digitalmindwebservices.service.impl.DigitalProfileServiceImpl;
import com.example.digitalmindwebservices.service.impl.ProjectServiceImpl;
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

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = ProjectController.class)
@ActiveProfiles("test")
public class ProjectControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ProjectServiceImpl projectService;
    @MockBean
    private DigitalProfileServiceImpl digitalProfileService;

    private List<Project> projectList;

    @BeforeEach
    void setUp() {
        projectList = new ArrayList<>();
        projectList.add(new Project(1L, "Project 1", "Description 1", "https://link.com", "https://link.com", new DigitalProfile(1L, "Digital Profile 1")));
        projectList.add(new Project(2L, "Project 2", "Description 2", "https://link.com", "https://link.com", new DigitalProfile(1L, "Digital Profile 1")));
        projectList.add(new Project(3L, "Project 3", "Description 3", "https://link.com", "https://link.com", new DigitalProfile(2L, "Digital Profile 2")));
        projectList.add(new Project(4L, "Project 4", "Description 4", "https://link.com", "https://link.com", new DigitalProfile(2L, "Digital Profile 2")));
    }

    @Test
    void findAllProjectsTest() throws Exception {
        given(projectService.getAll()).willReturn(projectList);
        mockMvc.perform(get("/api/v1/projects")).andExpect(status().isOk());
    }

    @Test
    void findProjectByIdTest() throws Exception {
        Long id = 1L;
        Project project = new Project(1L, "Project 1", "Description 1", "https://link.com", "https://link.com", new DigitalProfile(1L, "Digital Profile 1"));

        given(projectService.getById(id)).willReturn(Optional.of(project));
        mockMvc.perform(get("/api/v1/projects/{id}", id)).andExpect(status().isOk());
    }

    @Test
    void insertProjectTest() throws Exception {
        Long educationId = 1L;
        Project project = new Project(1L, "Project 1", "Description 1", "https://link.com", "https://link.com", new DigitalProfile(1L, "Digital Profile 1"));

        given(digitalProfileService.getById(educationId)).willReturn(Optional.of(project.getDigitalProfile()));
        mockMvc.perform(post("/api/v1/projects/{id}", educationId)
                        .content(asJsonString(project))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isCreated());
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
