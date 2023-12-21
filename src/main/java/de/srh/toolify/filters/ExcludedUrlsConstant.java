package de.srh.toolify.filters;

import java.util.Arrays;
import java.util.List;

public class ExcludedUrlsConstant {
    public static final List<String> EXCLUDED_URLS = Arrays.asList(
            "/public/products/**",
            "/public/users/user",
            "/public/categories/**",
            "/v2/api-docs",
            "/configuration/ui",
            " /swagger-resources/**",
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/v2/api-docs/**",
            "/swagger-resources/**",
            "swagger-ui/**",
            "/swagger-ui.html"
    );

}
