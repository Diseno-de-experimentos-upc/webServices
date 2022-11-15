package com.example.digitalmindwebservices.service.impl;

import com.example.digitalmindwebservices.entities.Framework;
import com.example.digitalmindwebservices.repository.IFrameworkRepository;
import com.example.digitalmindwebservices.service.IFrameworkService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class FrameworkServiceImpl implements IFrameworkService {
    private final IFrameworkRepository frameworkRepository;

    public FrameworkServiceImpl(IFrameworkRepository frameworkRepository) {
        this.frameworkRepository = frameworkRepository;
    }

    @Override
    @Transactional
    public Framework save(Framework framework) throws Exception {
        return frameworkRepository.save(framework);
    }

    @Override
    @Transactional
    public void delete(Long id) throws Exception {
        frameworkRepository.deleteById(id);
    }

    @Override
    public List<Framework> getAll() throws Exception {
        return frameworkRepository.findAll();
    }

    @Override
    public Optional<Framework> getById(Long id) throws Exception {
        return frameworkRepository.findById(id);
    }

    @Override
    public List<Framework> findByDigitalProfileId(Long id) {
        return frameworkRepository.findByDigitalProfileId(id);
    }
}
