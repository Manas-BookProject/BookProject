package com.manas.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record RegisterRequest(
        @NotNull(message = "first name should not be null")
        String firstName,
        @NotNull(message = "last name should not be null")
        String lastName,
        @NotNull(message = "phone number should not be null")
        @Pattern(regexp = "\\+996\\d{9}", message = "Phone number should start with +996 and consist of 13 characters!")
        String phoneNumber,

        @NotNull(message = "email should not be null")
        String email,
        @NotNull(message = "password should not be null")
        @Size(min = 4, message = "password should be more than 4 characters")
        String password
){

}
