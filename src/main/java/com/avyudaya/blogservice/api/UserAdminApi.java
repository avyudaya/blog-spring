package com.avyudaya.blogservice.api;

import com.avyudaya.blogservice.domain.dto.CreateUserReq;
import com.avyudaya.blogservice.domain.dto.UpdateUserReq;
import com.avyudaya.blogservice.domain.dto.UserView;
import com.avyudaya.blogservice.domain.model.Role;
import com.avyudaya.blogservice.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.RolesAllowed;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@Tag(name = "UserAdmin")
@RestController
@RequestMapping(path = "api/admin/user")
@RolesAllowed(Role.ADMIN)
@RequiredArgsConstructor
public class UserAdminApi {

    private final UserService userService;

    @PostMapping
    public UserView create(@RequestBody @Valid CreateUserReq request) {
        return userService.create(request);
    }

    @PutMapping("{id}")
    public UserView update(@PathVariable String id, @RequestBody @Valid UpdateUserReq request) {
        return userService.update(id, request);
    }

    @DeleteMapping("{id}")
    public UserView delete(@PathVariable String id) {
        return userService.delete(id);
    }

    @GetMapping("{id}")
    public UserView get(@PathVariable String id) {
        return userService.getUser(id);
    }

}
