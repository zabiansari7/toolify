package de.srh.toolify.utils;

import org.springframework.security.core.Authentication;

public class HelperUtil {
    public static boolean isAuthenticated(Authentication authentication) {
        return authentication != null && authentication.isAuthenticated();
    }
}
