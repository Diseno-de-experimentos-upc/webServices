package com.example.digitalmindwebservices.controller;

import com.example.digitalmindwebservices.entities.DigitalProfile;
import com.example.digitalmindwebservices.entities.Education;
import com.example.digitalmindwebservices.service.impl.DigitalProfileServiceImpl;
import com.example.digitalmindwebservices.service.impl.EducationServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = EducationController.class)
public class EducationControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private EducationServiceImpl educationService;
    @MockBean
    private DigitalProfileServiceImpl digitalProfileService;
    private List<Education> educationList;

    @BeforeEach
    void setUp() {

        educationList = new ArrayList<>();
        educationList.add(new Education(1L, "Software Engineering", new DigitalProfile(1L, "firstProfile")));
        educationList.add(new Education(2L, "Computer Science", new DigitalProfile(2L, "secondProfile")));
        educationList.add(new Education(3L, "Computer Engineering", new DigitalProfile(3L, "thirdProfile")));
        educationList.add(new Education(4L, "Software Engineering", new DigitalProfile(4L, "fourthProfile")));
    }

    @Test
    void findAllEducationTest() throws Exception {
        given(educationService.getAll()).willReturn(educationList);
        mockMvc.perform(get("/api/v1/educations")).andExpect(status().isOk());
    }

    @Test
    void findEducationByIdTest() throws Exception {
        Long id = 1L;
        Education education = new Education(1L, "Software Engineering", new DigitalProfile(1L, "firstProfile"));
        given(educationService.getById(id)).willReturn(Optional.of(education));
        mockMvc.perform(get("/api/v1/educations/{id}", id)).andExpect(status().isOk());
     }

    @Test
    void insertEducationTest() throws Exception {
        Long digitalProfileId = 1L;
        Education education = new Education(1L, "Software Engineering", new DigitalProfile(1L, "firstProfile"));
        given(digitalProfileService.getById(digitalProfileId)).willReturn(Optional.of(education.getDigitalProfile()));
        mockMvc.perform(post("/api/v1/educations/{id}", digitalProfileId)
                .content(asJsonString(education))
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
