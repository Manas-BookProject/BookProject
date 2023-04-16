package com.manas.dto.response;

import lombok.Builder;
import org.springframework.http.HttpStatus;

@Builder
public record ExceptionResponse(HttpStatus httpStatus,
                                String message,
                                String exceptionClassName) {

}