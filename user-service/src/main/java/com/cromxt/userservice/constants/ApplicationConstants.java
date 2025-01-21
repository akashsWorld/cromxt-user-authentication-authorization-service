package com.cromxt.userservice.constants;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class ApplicationConstants {

    public static final String REFRESH_TOKEN_ENDPOINT = "/tokens/refresh-access-token";

    private final String BASE_URL;
    private final String HOME_URL;

    public ApplicationConstants(Environment environment) {
        BASE_URL = environment.getProperty("server.servlet.context-path", String.class,"");
        HOME_URL = environment.getProperty("USER_CONFIG_CROMXT_HOME", String.class,"localhost:8080");
    }

    public String getBaseUrl() {
        return BASE_URL;
    }

    public String getHomeUrl() {
        return HOME_URL;
    }

}
