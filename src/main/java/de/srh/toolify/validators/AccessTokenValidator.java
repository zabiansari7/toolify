package de.srh.toolify.validators;

import java.util.HashSet;
import java.util.Set;

public class AccessTokenValidator {
	
	private static final Set<String> validTokens = new HashSet<>();

    public static boolean isValidToken(String token) {
        return validTokens.contains(token);
    }
    
    // Logic is only one token at a time for simplicity
    public static String getValidToken() {
        return validTokens.iterator().next();
    }

    public static void addValidToken(String token) {
        validTokens.add(token);
    }

    public static void removeToken(String token) {
        validTokens.remove(token);
    }

}
