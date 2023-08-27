package com.avyudaya.blogservice.domain.mapper;

import com.avyudaya.blogservice.domain.dto.UserView;
import com.avyudaya.blogservice.domain.exception.NotFoundException;
import com.avyudaya.blogservice.domain.model.User;
import com.avyudaya.blogservice.repository.UserRepository;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class UserViewMapper {

    @Autowired
    private UserRepository userRepo;

    public abstract UserView toUserView(User user);

    public abstract List<UserView> toUserView(List<User> users);

    public UserView toUserViewById(String id) {
        if (id == null) {
            return null;
        }
        return toUserView(userRepo.findById(id).orElseThrow(() -> new NotFoundException("User not found.")));
    }

}