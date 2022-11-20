package com.example.digitalmindwebservices.service.impl;

import com.example.digitalmindwebservices.entities.DigitalProfile;
import com.example.digitalmindwebservices.repository.IDigitalProfileRepository;
import com.example.digitalmindwebservices.service.IDigitalProfileService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class DigitalProfileServiceImpl implements IDigitalProfileService {

    private final IDigitalProfileRepository digitalProfileRepository;

    public DigitalProfileServiceImpl(IDigitalProfileRepository digitalProfileRepository) {
        this.digitalProfileRepository = digitalProfileRepository;
    }

    @Override
    @Transactional
    public DigitalProfile save(DigitalProfile digitalProfile) throws Exception {
        return digitalProfileRepository.save(digitalProfile);
    }

    @Override
    @Transactional
    public void delete(Long id) throws Exception {
        digitalProfileRepository.deleteById(id);
    }

    @Override
    public List<DigitalProfile> getAll() throws Exception {
        return digitalProfileRepository.findAll();
    }

    @Override
    public Optional<DigitalProfile> getById(Long id) throws Exception {
        return digitalProfileRepository.findById(id);
    }

    @Override
    public Optional<DigitalProfile> findDigitalProfileByDeveloperId(Long id) throws Exception {
        return digitalProfileRepository.findDigitalProfileByDeveloperId(id);
    }
}
