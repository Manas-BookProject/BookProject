package com.manas.dto.response;

import lombok.Builder;

@Builder
public record AuthenticationResponse(
        String login,
        String token,
        String role
) {
}