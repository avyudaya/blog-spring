package com.avyudaya.blogservice.domain.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.HashSet;
import java.util.Set;

public record CreateUserReq(
        @NotBlank @Email String username,
        @NotBlank String fullName,
        @NotBlank String password,
        @NotBlank String rePassword,
        Set<String> authorities) {

    public CreateUserReq {
        if (authorities == null) {
            authorities = new HashSet<>();
        }
    }

    public CreateUserReq(
            String username,
            String fullName,
            String password,
            String rePassword
    ) {
        this(username, fullName, password, rePassword, new HashSet<>());
    }

    public CreateUserReq(
            String username,
            String fullName,
            String password
    ) {
        this(username, fullName, password, password, new HashSet<>());
    }
}
