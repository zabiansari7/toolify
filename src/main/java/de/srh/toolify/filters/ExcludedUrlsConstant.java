package de.srh.toolify.filters;

import java.util.Arrays;
import java.util.List;

public class ExcludedUrlsConstant {
    public static final List<String> EXCLUDED_URLS = Arrays.asList(
            "/public/products/**",
            "/public/categories/**",
            "/v2/api-docs",
            "/configuration/ui",
            " /swagger-resources/**"
    );

}
