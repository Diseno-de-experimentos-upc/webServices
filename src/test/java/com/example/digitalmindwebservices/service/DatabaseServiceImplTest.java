package com.example.digitalmindwebservices.service;

import com.example.digitalmindwebservices.entities.Database;
import com.example.digitalmindwebservices.repository.IDatabaseRepository;
import com.example.digitalmindwebservices.service.impl.DatabaseServiceImpl;
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
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
public class DatabaseServiceImplTest {
    @Mock
    private IDatabaseRepository databaseRepository;
    @InjectMocks
    private DatabaseServiceImpl databaseService;

    @Test
    public void saveTest(){
        Database database = new Database(1L, "MySQL","https://th.bing.com/th/id/R.754e01e49836a295574661a188161775?rik=nVUmWi%2feQaZaSw&pid=ImgRaw&r=0","MySQL is a relational database management system based on SQL – Structured Query Language.");
        given(databaseRepository.save(database)).willReturn(database);

        Database savedDatabase = null;
        try {
            savedDatabase = databaseService.save(database);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertThat(savedDatabase).isNotNull();
        assertEquals(database, savedDatabase);
    }

    @Test
    public void deleteTest() throws Exception {
        Long id = 1L;
        databaseService.delete(id);
        verify(databaseRepository, times(1)).deleteById(id);
    }

    @Test
    public void getAllTest() throws Exception {
        List<Database> list = new ArrayList<>();
        list.add(new Database(1L, "MySQL","https://th.bing.com/th/id/R.754e01e49836a295574661a188161775?rik=nVUmWi%2feQaZaSw&pid=ImgRaw&r=0","MySQL is a relational database management system based on SQL – Structured Query Language."));
        list.add(new Database(2L, "PostgreSQL","https://wiki.postgresql.org/images/3/30/PostgreSQL_logo.3colors.120x120.png","PostgreSQL is a powerful, open source object-relational database system."));
        list.add(new Database(3L, "Oracle","https://download.logo.wine/logo/Oracle_Database/Oracle_Database-Logo.wine.png","Oracle Database is a multi-model database management system produced and marketed by Oracle Corporation."));
        given(databaseRepository.findAll()).willReturn(list);

        List<Database> foundList = databaseService.getAll();
        assertThat(foundList).isNotNull();
        assertEquals(list, foundList);
    }

    @Test
    public void getByIdTest() throws Exception {
        Long id = 1L;
        Database database = new Database(1L, "MySQL","https://th.bing.com/th/id/R.754e01e49836a295574661a188161775?rik=nVUmWi%2feQaZaSw&pid=ImgRaw&r=0","MySQL is a relational database management system based on SQL – Structured Query Language.");
        given(databaseRepository.findById(id)).willReturn(Optional.of(database));
        Optional<Database> databaseExpected = databaseService.getById(id);
        assertThat(databaseExpected).isNotNull();
        assertEquals(Optional.of(database), databaseExpected);
    }
}
