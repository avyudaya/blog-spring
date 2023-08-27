package com.avyudaya.blogservice.repository;

import com.avyudaya.blogservice.domain.model.Blog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepository extends CrudRepository<Blog, String> {
}
