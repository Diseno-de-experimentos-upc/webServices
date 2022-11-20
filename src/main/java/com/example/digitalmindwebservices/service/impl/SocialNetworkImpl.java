package com.example.digitalmindwebservices.service.impl;

import com.example.digitalmindwebservices.entities.SocialNetwork;
import com.example.digitalmindwebservices.entities.User;
import com.example.digitalmindwebservices.repository.ISocialNetworkRepository;
import com.example.digitalmindwebservices.service.ISocialNetworkService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Id;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class SocialNetworkImpl implements ISocialNetworkService {
    private final ISocialNetworkRepository socialNetworkRepository;

    public SocialNetworkImpl(ISocialNetworkRepository socialNetworkRepository) {
        this.socialNetworkRepository = socialNetworkRepository;
    }

    @Override
    @Transactional
    public SocialNetwork save(SocialNetwork socialNetwork) throws Exception {
        return socialNetworkRepository.save(socialNetwork);
    }

    @Override
    @Transactional
    public void delete(Long id) throws Exception {
        socialNetworkRepository.deleteById(id);
    }

    @Override
    public List<SocialNetwork> getAll() throws Exception {
        return socialNetworkRepository.findAll();
    }

    @Override
    public Optional<SocialNetwork> getById(Long id) throws Exception {
        return socialNetworkRepository.findById(id);
    }

    @Override
    public List<SocialNetwork> findSocialNetworkByName(String name_social_network) throws Exception {
        return socialNetworkRepository.findSocialNetworkByName(name_social_network);
    }

    @Override
    public List<SocialNetwork> findByUserId(Long id) throws Exception {
        return socialNetworkRepository.findByUserId(id);
    }

}
