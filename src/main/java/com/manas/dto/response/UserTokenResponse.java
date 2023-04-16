package com.manas.dto.response;

import lombok.Builder;

@Builder
public record UserTokenResponse(
        String login,
        String token
) {
}