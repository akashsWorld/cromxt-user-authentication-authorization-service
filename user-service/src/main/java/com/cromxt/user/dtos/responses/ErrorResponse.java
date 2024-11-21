package com.cromxt.user.dtos.responses;

public record ErrorResponse(
        String message,
        String rootCause
) {
}
