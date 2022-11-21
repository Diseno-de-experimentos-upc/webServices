package com.example.digitalmindwebservices.service.impl;

import com.example.digitalmindwebservices.entities.Company;
import com.example.digitalmindwebservices.repository.ICompanyRepository;
import com.example.digitalmindwebservices.service.ICompanyService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class CompanyServiceImpl implements ICompanyService {

    private final ICompanyRepository _companyRepository;

    public CompanyServiceImpl(ICompanyRepository companyRepository) {
        _companyRepository = companyRepository;
    }

    @Override
    @Transactional
    public Company save(Company company) throws Exception {
        return _companyRepository.save(company);
    }

    @Override
    @Transactional
    public void delete(Long id) throws Exception {
        _companyRepository.deleteById(id);
    }

    @Override
    public List<Company> getAll() throws Exception {
        return _companyRepository.findAll();
    }

    @Override
    public Optional<Company> getById(Long id) throws Exception {
        return _companyRepository.findById(id);
    }
}
