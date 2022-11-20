package com.example.digitalmindwebservices.service.impl;

import com.example.digitalmindwebservices.entities.Database;
import com.example.digitalmindwebservices.repository.IDatabaseRepository;
import com.example.digitalmindwebservices.service.IDatabaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class DatabaseServiceImpl implements IDatabaseService {
    private final IDatabaseRepository databaseRepository;

    public DatabaseServiceImpl(IDatabaseRepository databaseRepository) {
        this.databaseRepository = databaseRepository;
    }

    @Override
    @Transactional
    public Database save(Database database) throws Exception {
        return databaseRepository.save(database);
    }

    @Override
    @Transactional
    public void delete(Long id) throws Exception {
        databaseRepository.deleteById(id);
    }

    @Override
    public List<Database> getAll() throws Exception {
        return databaseRepository.findAll();
    }

    @Override
    public Optional<Database> getById(Long id) throws Exception {
        return databaseRepository.findById(id);
    }

    @Override
    public List<Database> findByDigitalProfileId(Long id) throws Exception {
        return databaseRepository.findByDigitalProfileId(id);
    }

    @Override
    public List<Database> findByName(String name) throws Exception {
        return databaseRepository.findDatabasesByName(name);
    }

    @Override
    public List<Database> findByDeveloperId(Long id) throws Exception {
        return databaseRepository.findDatabasesByUserId(id);
    }
}
