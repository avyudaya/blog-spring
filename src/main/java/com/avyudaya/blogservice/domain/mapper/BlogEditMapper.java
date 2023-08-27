package com.avyudaya.blogservice.domain.mapper;

import com.avyudaya.blogservice.domain.dto.BlogEditReq;
import com.avyudaya.blogservice.domain.model.Blog;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface BlogEditMapper {
    Blog create(BlogEditReq request);

    @BeanMapping(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void update(BlogEditReq req, @MappingTarget Blog blog);
}
