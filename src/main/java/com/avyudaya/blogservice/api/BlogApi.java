package com.avyudaya.blogservice.api;

import com.avyudaya.blogservice.domain.dto.*;
import com.avyudaya.blogservice.service.BlogService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Blog")
@RestController
@RequestMapping(path = "api/blog")
@RequiredArgsConstructor
public class BlogApi {

    private final BlogService blogService;

    @PostMapping
    public BlogView create(@RequestBody @Valid BlogEditReq request) {
        return blogService.create(request);
    }

    @PutMapping("{id}")
    public BlogView update(@PathVariable String id, @RequestBody @Valid BlogEditReq request) {
        return blogService.update(id, request);
    }

    @DeleteMapping("{id}")
    public BlogView delete(@PathVariable String id) {
        return blogService.delete(id);
    }

    @GetMapping("{id}")
    public BlogView get(@PathVariable String id) {
        return blogService.get(id);
    }

}
