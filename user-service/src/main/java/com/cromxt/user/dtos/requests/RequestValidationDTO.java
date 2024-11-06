package com.cromxt.user.dtos.requests;

import org.apache.tomcat.util.codec.binary.StringUtils;

public record RequestValidationDTO (
        String serviceKey,
        String token
){
}
