package com.example.digitalmindwebservices.service;

import com.example.digitalmindwebservices.entities.DigitalProfile;
import com.example.digitalmindwebservices.entities.Education;
import com.example.digitalmindwebservices.repository.IEducationRepository;
import com.example.digitalmindwebservices.service.impl.EducationServiceImpl;
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
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class EducationServiceImplTest {
    @Mock
    private IEducationRepository educationRepository;
    @InjectMocks
    private EducationServiceImpl educationService;

    @Test
    public void saveTest() {
        Education education = new Education(1L, "Software Engineering", new DigitalProfile(1L, "firtsProfile"));

        given(educationRepository.save(education)).willReturn(education);
        Education savedEducation = null;

        try{
            savedEducation = educationService.save(education);
        }catch (Exception e){
            e.printStackTrace();
        }

        assertThat(savedEducation).isNotNull();
        assertEquals(education, savedEducation);
    }

    @Test
    public void deteleTest() throws Exception {

        Long id = 1L;
        educationService.delete(id);
        verify(educationRepository, times(1)).deleteById(id);
    }

    @Test
    public void getAllTest () throws Exception {
        List<Education> list = new ArrayList<>();
        list.add(new Education(1L, "Data Science", new DigitalProfile(1L, "firtsProfile")));
        list.add(new Education(2L, "Software Engineering", new DigitalProfile(2L, "secondProfile")));
        list.add(new Education(3L, "System Engineering", new DigitalProfile(3L, "thirdProfile")));

        given(educationRepository.findAll()).willReturn(list);
        List<Education> educationList = educationService.getAll();
        assertEquals(list, educationList);
    }

    @Test
    public void getByIdTest() throws Exception {
        Long id = 1L;
        Education education = new Education(1L, "Data Science", new DigitalProfile(1L, "firtsProfile"));
        given(educationRepository.findById(id)).willReturn(Optional.of(education));
        Optional<Education> expectedEducation = educationService.getById(id);
        assertEquals(Optional.of(education), expectedEducation);
    }
}
