package com.example.digitalmindwebservices.service;

import com.example.digitalmindwebservices.entities.DigitalProfile;
import com.example.digitalmindwebservices.entities.Education;
import com.example.digitalmindwebservices.entities.StudyCenter;
import com.example.digitalmindwebservices.repository.IStudyCenterRepository;
import com.example.digitalmindwebservices.service.impl.StudyCenterServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class StudyCenterServiceImplTest {

    @Mock
    private IStudyCenterRepository studyCenterRepository;
    @InjectMocks
    private StudyCenterServiceImpl studyCenterService;

    @Test
    public void saveTest() {
        StudyCenter studyCenter = new StudyCenter(1L, "Udemy",
                "https://img.com/aimage", 75, "Unity VR Course", new Date("2022/11/09"), new Date("2022/11/09"),
                new Education(1L, "Software Engineering", new DigitalProfile(1L, "firtsProfile")));

        given(studyCenterRepository.save(studyCenter)).willReturn(studyCenter);
        StudyCenter savedStudyCenter = null;

        try {
            savedStudyCenter = studyCenterService.save(studyCenter);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertThat(savedStudyCenter).isNotNull();
        assertEquals(studyCenter, savedStudyCenter);
    }

    @Test
    public void deleteTest() throws Exception {
        Long id = 1L;
        studyCenterService.delete(id);
        verify(studyCenterRepository, times(1)).deleteById(id);
    }

    @Test
    public void getAllTest() throws Exception{
        List<StudyCenter> studyCenters = new ArrayList<>();
        studyCenters.add(new StudyCenter(1L, "Udemy",
                "https://img.com/aimage", 75, "Unity VR Course", new Date("2022/11/09"), new Date("2022/11/09"),
                new Education(1L, "Software Engineering", new DigitalProfile(1L, "firtsProfile"))));
        studyCenters.add(new StudyCenter(2L, "XR Academy",
                "https://img.com/aimage", 30, "Augmented Reality Course", new Date("2022/11/09"), new Date("2022/11/09"),
                new Education(2L, "System Engineering", new DigitalProfile(1L, "secondProfile"))));
        studyCenters.add(new StudyCenter(3L, "Udemy",
                "https://img.com/aimage", 95, "Unity VR Course", new Date("2022/11/09"), new Date("2022/11/09"),
                new Education(3L, "Data Science", new DigitalProfile(1L, "thirdProfile"))));

        given(studyCenterRepository.findAll()).willReturn(studyCenters);
        List<StudyCenter> studyCentersList = studyCenterService.getAll();
        assertEquals(studyCenters, studyCentersList);
    }

    @Test
    public void getByIdTest() throws Exception {
        Long id = 1L;
        StudyCenter studyCenter = new StudyCenter(1L, "Udemy",
                "https://img.com/aimage", 75, "Unity VR Course", new Date("2022/11/09"), new Date("2022/11/09"),
                new Education(1L, "Software Engineering", new DigitalProfile(1L, "firtsProfile")));
        given(studyCenterRepository.findById(id)).willReturn(Optional.of(studyCenter));
        Optional<StudyCenter> foundStudyCenter = studyCenterService.getById(id);
        assertEquals(Optional.of(studyCenter), foundStudyCenter);
    }

}
