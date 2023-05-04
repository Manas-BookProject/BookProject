package com.manas.dto.response;

public record UserResponse(
        Long id,
        String firstName,
        String lastName,
        String phoneNumber,
        String email
) {
}
