package com.avyudaya.blogservice.service;

import com.avyudaya.blogservice.domain.dto.*;
import com.avyudaya.blogservice.domain.exception.NotFoundException;
import com.avyudaya.blogservice.domain.mapper.BlogEditMapper;
import com.avyudaya.blogservice.domain.mapper.BlogViewMapper;
import com.avyudaya.blogservice.repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class BlogService {
    private final BlogRepository blogRepository;
    private final BlogEditMapper blogEditMapper;
    private final BlogViewMapper blogViewMapper;

    @Transactional
    public BlogView create(BlogEditReq request) {
        var blog = blogEditMapper.create(request);
        return blogViewMapper.toBlogView(blogRepository.save(blog));
    }

    @Transactional
    public BlogView update(String id, BlogEditReq request) {
        var blog = blogRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found."));
        blogEditMapper.update(request, blog);
        blog = blogRepository.save(blog);
        return blogViewMapper.toBlogView(blogRepository.save(blog));
    }

    @Transactional
    public BlogView delete(String id) {
        var blog = blogRepository.findById(id).orElseThrow(() -> new NotFoundException("Blog not found"));
        blogRepository.delete(blog);
        return blogViewMapper.toBlogView(blog);
    }

    @Transactional
    public BlogView get(String id) {
        var blog = blogRepository.findById(id).orElseThrow(() -> new NotFoundException("Blog not found"));
        return blogViewMapper.toBlogView(blog);
    }
}
