package com.avyudaya.blogservice.domain.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

import java.util.Set;

public record UpdateUserReq(
        @NotBlank
        String fullName,
        Set<String> authorities
) {

    @Builder
    public UpdateUserReq {
    }

    public UpdateUserReq() {
        this(null, null);
    }
}
