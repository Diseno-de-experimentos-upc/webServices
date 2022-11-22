package com.example.digitalmindwebservices.cucumber;

import com.example.digitalmindwebservices.DigitalMindWebServicesApplication;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@CucumberContextConfiguration
@SpringBootTest( classes = DigitalMindWebServicesApplication.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = DigitalMindWebServicesApplication.class, loader = SpringBootContextLoader.class)
public class CucumberSpringConfiguration {
}
