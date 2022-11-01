package com.example.digitalmindwebservices.service;

import com.example.digitalmindwebservices.entities.Certificate;
import com.example.digitalmindwebservices.entities.DigitalProfile;
import com.example.digitalmindwebservices.entities.Education;
import com.example.digitalmindwebservices.repository.ICertificateRepository;
import com.example.digitalmindwebservices.service.impl.CertificateServiceImpl;
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
public class CertificateServiceImplTest {

    @Mock
    private ICertificateRepository certificateRepository;
    @InjectMocks
    private CertificateServiceImpl certificateService;

    @Test
    public void saveTest() {
        Certificate certificate = new Certificate(1L, "Udemy", "Unity VR Course",
                "https://img.com/aimage", new Date("2022/11/09"),
                new Education(1L, "Software Engineering", new DigitalProfile(1L, "firtsProfile")));

        given(certificateRepository.save(certificate)).willReturn(certificate);
        Certificate savedCertificate = null;

        try{
            savedCertificate = certificateService.save(certificate);
        }catch (Exception e){
            e.printStackTrace();
        }

        assertThat(savedCertificate).isNotNull();
        assertEquals(certificate, savedCertificate);

    }

    @Test
    public void deleteTest() throws Exception {
        Long id = 1L;
        certificateService.delete(id);
        verify(certificateRepository, times(1)).deleteById(id);
    }

    @Test
    public void getAllTest() throws Exception {
        List<Certificate> list = new ArrayList<>();

        list.add(new Certificate(1L, "Udemy", "Unity VR Course", "https://img.com/aimage",
                new Date("2022/11/09"),
                new Education(1L, "Software Engineering",
                new DigitalProfile(1L, "firtsProfile"))));
        list.add(new Certificate(2L, "Udemy", "Unity VR Course",
                "https://img.com/aimage", new Date("2022/11/09"),
                new Education(1L, "Software Engineering",
                new DigitalProfile(1L, "firtsProfile"))));
        list.add(new Certificate(3L, "Udemy", "Unity VR Course", "https://img.com/aimage",
                new Date("2022/11/09"),
                new Education(2L, "Software Engineering",
                new DigitalProfile(2L, "secondProfile"))));
        list.add(new Certificate(4L, "Udemy", "Unity VR Course", "https://img.com/aimage",
                new Date("2022/11/09"),
                new Education(2L, "Software Engineering",
                new DigitalProfile(2L, "secondProfile"))));

        given(certificateRepository.findAll()).willReturn(list);
        List<Certificate> expectedList = certificateService.getAll();
        assertEquals(expectedList, list);
    }

    @Test
    public void getByIdTest() throws Exception {
        Long id = 1L;
        Certificate certificate = new Certificate(1L, "Udemy", "Unity VR Course",
                "https://img.com/aimage", new Date("2022/11/09"),
                new Education(1L, "Software Engineering",
                new DigitalProfile(1L, "firtsProfile")));

        given(certificateRepository.findById(id)).willReturn(Optional.of(certificate));
        Optional<Certificate>expectedCertificate = certificateService.getById(id);
        assertThat(expectedCertificate).isNotNull();
        assertEquals(expectedCertificate, Optional.of(certificate));
    }

}
