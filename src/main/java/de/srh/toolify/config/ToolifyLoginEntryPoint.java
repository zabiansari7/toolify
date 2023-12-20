package de.srh.toolify.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;

public class ToolifyLoginEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        System.out.println("WAS DALLED");
        System.out.println("CHECK FOR AUTH STATUS :: " + SecurityContextHolder.getContext().getAuthentication());
        authException.printStackTrace();
        response.sendRedirect("http://localhost:8081/login");
    }
}
