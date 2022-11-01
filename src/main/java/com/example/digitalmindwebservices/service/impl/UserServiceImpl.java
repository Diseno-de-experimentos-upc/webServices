package com.example.digitalmindwebservices.service.impl;

import com.example.digitalmindwebservices.entities.User;
import com.example.digitalmindwebservices.repository.IUserRepository;
import com.example.digitalmindwebservices.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements IUserService {
    private final IUserRepository userRepository;
    public UserServiceImpl(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    @Transactional
    public User save(User user) throws Exception {
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public void delete(Long id) throws Exception {
        userRepository.deleteById(id);
    }

    @Override
    public List<User> getAll() throws Exception {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getById(Long id) throws Exception {
        return userRepository.findById(id);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> findByFirstName(String firstName) {
        return userRepository.findByFirstName(firstName);
    }


}
