package com.example.digitalmindwebservices.controller;

import com.example.digitalmindwebservices.entities.Certificate;
import com.example.digitalmindwebservices.entities.DigitalProfile;
import com.example.digitalmindwebservices.entities.Education;
import com.example.digitalmindwebservices.service.impl.CertificateServiceImpl;
import com.example.digitalmindwebservices.service.impl.DigitalProfileServiceImpl;
import com.example.digitalmindwebservices.service.impl.EducationServiceImpl;
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


@WebMvcTest(controllers = CertificateController.class)
@ActiveProfiles("test")
public class CertificateControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CertificateServiceImpl certificateService;
    @MockBean
    private EducationServiceImpl educationService;
    @MockBean
    private DigitalProfileServiceImpl digitalProfileService;

    private List<Certificate> certificateList;

    @BeforeEach
    void setUp() {
        certificateList = new ArrayList<>();

        certificateList.add(new Certificate(1L, "Udemy", "Unity VR Course", "https://img.com/aimage",
                new Date(20221109),
                new Education(1L, "Software Engineering",
                        new DigitalProfile(1L, "firstProfile"))));
        certificateList.add(new Certificate(2L, "Udemy", "Unity VR Course",
                "https://img.com/aimage", new Date(20221109),
                new Education(1L, "Software Engineering",
                        new DigitalProfile(1L, "firstProfile"))));
        certificateList.add(new Certificate(3L, "Udemy", "Unity VR Course", "https://img.com/aimage",
                new Date(20221109),
                new Education(2L, "Software Engineering",
                        new DigitalProfile(2L, "secondProfile"))));
        certificateList.add(new Certificate(4L, "Udemy", "Unity VR Course", "https://img.com/aimage",
                new Date(20221109),
                new Education(2L, "Software Engineering",
                        new DigitalProfile(2L, "secondProfile"))));
    }

    @Test
    void findAllCertificatesTest() throws Exception {
        given(certificateService.getAll()).willReturn(certificateList);
        mockMvc.perform(get("/api/v1/certificates")).andExpect(status().isOk());
    }

    @Test
    void findCertificateByIdTest() throws Exception {
         Long id = 1L;
         Certificate certificate = new Certificate(1L, "Udemy", "Unity VR Course", "https://img.com/aimage",
                 new Date(20221109),new Education(1L, "Software Engineering",
                         new DigitalProfile(1L, "firstProfile")));
            given(certificateService.getById(id)).willReturn(Optional.of(certificate));
            mockMvc.perform(get("/api/v1/certificates/{id}", id)).andExpect(status().isOk());
    }

    @Test
    void insertCertificateTest() throws Exception {
        Long educationId = 1L;
        Certificate certificate = new Certificate(1L, "Udemy", "Unity VR Course", "https://img.com/aimage",
                new Date(20221109),new Education(1L, "Software Engineering",
                        new DigitalProfile(1L, "firstProfile")));

        given(educationService.getById(educationId)).willReturn(Optional.of(certificate.getEducation()));
        mockMvc.perform(post("/api/v1/certificates/{id}", educationId)
                .content(asJsonString(certificate))
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
