package com.avyudaya.blogservice.domain.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record AuthReq(
        @NotNull @Email String username,
        @NotNull String password) {

    public AuthReq() {
        this(null, null);
    }
}
