package com.example.digitalmindwebservices.service.impl;

import com.example.digitalmindwebservices.entities.ProgrammingLanguage;
import com.example.digitalmindwebservices.repository.IProgrammingLanguageRepository;
import com.example.digitalmindwebservices.service.IProgrammingLanguageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ProgrammingLanguageImpl implements IProgrammingLanguageService {
    private final IProgrammingLanguageRepository programmingLanguageRepository;

    public ProgrammingLanguageImpl(IProgrammingLanguageRepository programmingLanguageRepository) {
        this.programmingLanguageRepository = programmingLanguageRepository;
    }
    @Override
    @Transactional
    public ProgrammingLanguage save(ProgrammingLanguage programmingLanguage) throws Exception {
        return programmingLanguageRepository.save(programmingLanguage);
    }

    @Override
    @Transactional
    public void delete(Long id) throws Exception {
        programmingLanguageRepository.deleteById(id);
    }

    @Override
    public List<ProgrammingLanguage> getAll() throws Exception {
        return programmingLanguageRepository.findAll();
    }

    @Override
    public Optional<ProgrammingLanguage> getById(Long id) throws Exception {
        return programmingLanguageRepository.findById(id);
    }

    @Override
    public List<ProgrammingLanguage> findByDigitalProfileId(Long id) throws Exception {
        return programmingLanguageRepository.findByDigitalProfileId(id);
    }

    @Override
    public List<ProgrammingLanguage> findByName(String name) throws Exception {
        return programmingLanguageRepository.findProgrammingLanguagesByName(name);
    }

    @Override
    public List<ProgrammingLanguage> findByDeveloperId(Long id) throws Exception {
        return programmingLanguageRepository.findProgrammingLanguagesByUserId(id);
    }
}
