package com.example.digitalmindwebservices.controller;

import com.example.digitalmindwebservices.entities.Database;
import com.example.digitalmindwebservices.service.impl.DatabaseServiceImpl;
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

@WebMvcTest(controllers = UserController.class)
@ActiveProfiles("test")
public class DatabaseControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private DatabaseServiceImpl databaseService;
    private List<Database> databaseList;

    @BeforeEach
    void setUp() {
        databaseList = new ArrayList<>();
        databaseList.add(new Database(1L, "Microsoft SQL Server",
                "https://th.bing.com/th/id/OIP.sluuRP9RbH3MPqzbFNLEmQHaF_?pid=ImgDet&rs=1", "Microsoft SQL Server is a relational database management system developed by Microsoft"));
        databaseList.add(new Database(2L, "Oracle Database",
                "https://download.logo.wine/logo/Oracle_Database/Oracle_Database-Logo.wine.png", "Oracle Database is a multi-model database management system produced and marketed by Oracle Corporation"));
        databaseList.add(new Database(3L, "PostgreSQL",
                "https://wiki.postgresql.org/images/3/30/PostgreSQL_logo.3colors.120x120.png", "Postgres, is a free and open-source relational database management system (RDBMS) emphasizing extensibility and SQL compliance"));
        databaseList.add(new Database(4L, "MySQL",
                "https://download.logo.wine/logo/MySQL/MySQL-Logo.wine.png", "MySQL is an open-source relational database management system (RDBMS)"));
    }

    @Test
    void findAllDatabasesTest() throws Exception {
        given(databaseService.getAll()).willReturn(databaseList);
        mockMvc.perform(get("/api/v1/databases")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void findDatabaseByIdTest() throws Exception {
        Long id = 1L;
        given(databaseService.getById(id)).willReturn(Optional.of(databaseList.get(0)));
        mockMvc.perform(get("/api/v1/databases/{id}", id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void insertDatabaseTest() throws Exception {
        Database database = new Database(5L, "MongoDB",
                "https://tjth.co/wp-content/uploads/2018/06/mongodb-logo.png", "MongoDB is a cross-platform document-oriented database program. Classified as a NoSQL database program, MongoDB uses JSON-like documents with schemas.");
        given(databaseService.save(database)).willReturn(database);
        mockMvc.perform(post("/api/v1/databases")
                .content(asJsonString(database))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isCreated());
    }

    @Test
    public void updateDatabaseTest() throws Exception {
        Long id = 1L;
        Database database = new Database(1L, "MongoDB",
                "https://tjth.co/wp-content/uploads/2018/06/mongodb-logo.png", "MongoDB is a cross-platform document-oriented database program. Classified as a NoSQL database program, MongoDB uses JSON-like documents with schemas.");
        given(databaseService.getById(id)).willReturn(Optional.of(database));
        mockMvc.perform(put("/api/v1/databases/{id}", id)
                .content(asJsonString(database))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteDatabaseTest() throws Exception {
        Long id = 1L;
        mockMvc.perform(delete("/api/v1/databases/{id}", id)
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
