package com.example.digitalmindwebservices.service.impl;

import com.example.digitalmindwebservices.entities.Post;
import com.example.digitalmindwebservices.repository.IPostRepository;
import com.example.digitalmindwebservices.service.IPostService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements IPostService {
    private final IPostRepository postRepository;

    public PostServiceImpl(IPostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    @Transactional
    public Post save(Post post) throws Exception {
        return postRepository.save(post);
    }

    @Override
    @Transactional
    public void delete(Long id) throws Exception {
        postRepository.deleteById(id);
    }

    @Override
    public List<Post> getAll() throws Exception {
        return postRepository.findAll();
    }

    @Override
    public Optional<Post> getById(Long id) throws Exception {
        return postRepository.findById(id);
    }

    @Override
    public List<Post> findByCompany_Id(Long company_id) throws Exception {
        return postRepository.findByCompany_Id(company_id);
    }


}
