package com.avyudaya.blogservice.domain.mapper;

import com.avyudaya.blogservice.domain.dto.BlogView;
import com.avyudaya.blogservice.domain.model.Blog;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BlogViewMapper {
    BlogView toBlogView(Blog blog);
}
