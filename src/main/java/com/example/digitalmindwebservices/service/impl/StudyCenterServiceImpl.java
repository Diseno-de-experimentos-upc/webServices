package com.example.digitalmindwebservices.service.impl;

import com.example.digitalmindwebservices.entities.StudyCenter;
import com.example.digitalmindwebservices.repository.IStudyCenterRepository;
import com.example.digitalmindwebservices.service.IStudyCenterService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class StudyCenterServiceImpl implements IStudyCenterService {

    private final IStudyCenterRepository studyCenterRepository;

    public StudyCenterServiceImpl(IStudyCenterRepository studyCenterRepository) {
        this.studyCenterRepository = studyCenterRepository;
    }

    @Override
    @Transactional
    public StudyCenter save(StudyCenter studyCenter) throws Exception {
        return studyCenterRepository.save(studyCenter);
    }

    @Override
    @Transactional
    public void delete(Long id) throws Exception {
        studyCenterRepository.deleteById(id);
    }

    @Override
    public List<StudyCenter> getAll() throws Exception {
        return studyCenterRepository.findAll();
    }

    @Override
    public Optional<StudyCenter> getById(Long id) throws Exception {
        return studyCenterRepository.findById(id);
    }
}
