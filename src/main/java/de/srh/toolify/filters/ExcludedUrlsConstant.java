package de.srh.toolify.filters;

import java.util.Arrays;
import java.util.List;

public class ExcludedUrlsConstant {
    public static final List<String> EXCLUDED_URLS = Arrays.asList(
            "/public/products/**",            // Exclude URLs starting with "/public/"
            "/public/categories/**",              // Exclude the "/login" URL
            "/v2/api-docs",        // Exclude a specific URL
            "/configuration/ui",       // Exclude URLs matching a wildcard pattern
            " /swagger-resources/**"
    );

}
