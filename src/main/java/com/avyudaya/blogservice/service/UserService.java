package com.avyudaya.blogservice.service;

import com.avyudaya.blogservice.domain.dto.CreateUserReq;
import com.avyudaya.blogservice.domain.dto.UpdateUserReq;
import com.avyudaya.blogservice.domain.dto.UserView;
import com.avyudaya.blogservice.domain.exception.NotFoundException;
import com.avyudaya.blogservice.domain.mapper.UserEditMapper;
import com.avyudaya.blogservice.domain.mapper.UserViewMapper;
import com.avyudaya.blogservice.repository.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserEditMapper userEditMapper;
    private final UserViewMapper userViewMapper;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserView create(CreateUserReq request) {
        if (userRepository.findByUsername(request.username()).isPresent()) {
            throw new ValidationException("Username exists!");
        }
        if (!request.password().equals(request.rePassword())) {
            throw new ValidationException("Passwords don't match!");
        }

        var user = userEditMapper.create(request);
        user.setPassword(passwordEncoder.encode(request.password()));

        user = userRepository.save(user);

        return userViewMapper.toUserView(user);
    }

    @Transactional
    public UserView update(String id, UpdateUserReq request) {
        var user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found."));
        userEditMapper.update(request, user);
        user = userRepository.save(user);
        return userViewMapper.toUserView(user);
    }

    @Transactional
    public void upsert(CreateUserReq request) {
        var optionalUser = userRepository.findByUsername(request.username());
        if (optionalUser.isEmpty()) {
            create(request);
        } else {
            UpdateUserReq updateUserRequest =
                    new UpdateUserReq(request.fullName(), request.authorities());
            update(optionalUser.get().getId(), updateUserRequest);
        }
    }

    @Transactional
    public UserView delete(String id) {
        var user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found."));

        user.setUsername(
                user.getUsername().replace("@", String.format("_%s@", user.getId().toString())));
        user.setEnabled(false);
        user = userRepository.save(user);

        return userViewMapper.toUserView(user);
    }

    public UserView getUser(String id) {
        return userViewMapper.toUserView(userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found.")));
    }
}
