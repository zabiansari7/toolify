package de.srh.toolify.config;


public class ToolifyAuthentication {
    private static String email;

    private static String password;

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        ToolifyAuthentication.email = email;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        ToolifyAuthentication.password = password;
    }
}
