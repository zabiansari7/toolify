package de.srh.toolify.filters;

import java.util.Arrays;
import java.util.List;

public class ExcludedUrlsConstant {
    public static final List<String> EXCLUDED_URLS = Arrays.asList(
            "/public/**",            // Exclude URLs starting with "/public/"
            "/private/admin/**",                // Exclude the "/login" URL
            "/v2/api-docs",        // Exclude a specific URL
            "/configuration/ui",       // Exclude URLs matching a wildcard pattern
            " /swagger-resources/**"
    );

}
