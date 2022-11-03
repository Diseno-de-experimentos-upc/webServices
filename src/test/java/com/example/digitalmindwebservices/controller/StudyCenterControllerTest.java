package com.example.digitalmindwebservices.controller;

import com.example.digitalmindwebservices.entities.DigitalProfile;
import com.example.digitalmindwebservices.entities.Education;
import com.example.digitalmindwebservices.entities.StudyCenter;
import com.example.digitalmindwebservices.service.impl.EducationServiceImpl;
import com.example.digitalmindwebservices.service.impl.StudyCenterServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = StudyCenterController.class)
@ActiveProfiles("test")
public class StudyCenterControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private StudyCenterServiceImpl studyCenterService;
    @MockBean
    private EducationServiceImpl educationService;

    private List<StudyCenter> studyCentersList;

    @BeforeEach
    void setUp() {
        studyCentersList = new ArrayList<>();
        studyCentersList.add(new StudyCenter(1L, "Columnia University", "https://logo.com/", 90,"Bachelors in Software Engineering", new Date(20201007), new Date(20251007), new Education(1L, "Software Engineering", new DigitalProfile(1L, "first Proffile"))));
        studyCentersList.add(new StudyCenter(2L, "UPC University", "https://logo.com/", 90,"Bachelors in Software Engineering", new Date(20201007), new Date(20251007), new Education(1L, "Software Engineering", new DigitalProfile(1L, "first Proffile"))));
        studyCentersList.add(new StudyCenter(3L, "Hardvard University", "https://logo.com/", 90,"Bachelors in Software Engineering", new Date(20201007), new Date(20251007), new Education(2L, "Software Engineering", new DigitalProfile(2L, "first Proffile"))));
        studyCentersList.add(new StudyCenter(4L, "Columnia University", "https://logo.com/", 90,"Bachelors in Software Engineering", new Date(20201007), new Date(20251007), new Education(2L, "Software Engineering", new DigitalProfile(2L, "first Proffile"))));
    }

    @Test
    void findAllStudyCentersTest() throws Exception {
        given(studyCenterService.getAll()).willReturn(studyCentersList);
        mockMvc.perform(get("/api/v1/study-centers")).andExpect(status().isOk());
    }

    @Test
    void findStudyCenterByIdTest() throws Exception {
        Long id = 1L;
        StudyCenter studyCenter = new StudyCenter(1L, "Columnia University", "https://logo.com/", 90,"Bachelors in Software Engineering", new Date(20201007), new Date(20251007), new Education(1L, "Software Engineering", new DigitalProfile(1L, "first Proffile")));
        given(studyCenterService.getById(id)).willReturn(Optional.of(studyCenter));
        mockMvc.perform(get("/api/v1/study-centers/{id}", id)).andExpect(status().isOk());
    }

    @Test
    void insertStudyCenterTest() throws Exception {
        Long educationId = 1L;
        StudyCenter studyCenter = new StudyCenter(1L, "Columnia University", "https://logo.com/", 90,"Bachelors in Software Engineering", new Date(20201007), new Date(20251007), new Education(1L, "Software Engineering", new DigitalProfile(1L, "first Proffile")));

        given(educationService.getById(educationId)).willReturn(Optional.of(studyCenter.getEducation()));
        mockMvc.perform(post("/api/v1/study-centers/{id}", educationId)
                        .content(asJsonString(studyCenter))
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
