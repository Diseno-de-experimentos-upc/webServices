package com.example.digitalmindwebservices.service;

import com.example.digitalmindwebservices.entities.DigitalProfile;
import com.example.digitalmindwebservices.entities.Project;
import com.example.digitalmindwebservices.repository.IProjectRepository;
import com.example.digitalmindwebservices.service.impl.ProjectServiceImpl;
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
public class ProjectServiceImplTest {

    @Mock
    private IProjectRepository projectRepository;
    @InjectMocks
    private ProjectServiceImpl projectService;

    @Test
    public void saveTest() {
        Project project = new Project(1L, "Project 1", "Description 1", "https://img.com/aimage", "https://myproject.com/webapp",
                new DigitalProfile(1L, "firtsProfile"));

        given(projectRepository.save(project)).willReturn(project);
        Project savedProject = null;

        try{
            savedProject = projectService.save(project);
        }catch (Exception e){
            e.printStackTrace();
        }

        assertThat(savedProject).isNotNull();
        assertEquals(project, savedProject);
    }

    @Test
    public void deleteTest () throws Exception {

        Long id = 1L;
        projectService.delete(id);
        verify(projectRepository, times(1)).deleteById(id);
    }

    @Test
    public void geyAllTes() throws Exception {
        List<Project> list = new ArrayList<>();
        list.add(new Project(1L, "Project 1", "Description 1", "https://img.com/aimage", "https://myproject.com/webapp",
                new DigitalProfile(1L, "firtsProfile")));
        list.add(new Project(2L, "Project 2", "Description 2", "https://img.com/aimage", "https://myproject.com/webapp",
                new DigitalProfile(2L, "firtsProfile")));
        list.add(new Project(3L, "Project 3", "Description 3", "https://img.com/aimage", "https://myproject.com/webapp",
                new DigitalProfile(2L, "firtsProfile")));

        given(projectRepository.findAll()).willReturn(list);
        List<Project> projects = projectService.getAll();
        assertEquals(list, projects);
    }

    @Test
    public void getByIdTest() throws Exception {
        Long id = 1L;
        Project project = new Project(1L, "Project 1", "Description 1", "https://img.com/aimage", "https://myproject.com/webapp",
                new DigitalProfile(1L, "firtsProfile"));

        given(projectRepository.findById(id)).willReturn(Optional.of(project));
        Optional<Project> expectedProject = projectService.getById(id);
        assertEquals(Optional.of(project),  expectedProject);
    }

}
