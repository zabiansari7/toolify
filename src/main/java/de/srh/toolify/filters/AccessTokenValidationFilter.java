package de.srh.toolify.filters;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.srh.toolify.config.ToolifyFailureAuthenticationHandler;
import de.srh.toolify.config.ToolifySuccessAuthenticationHandler;
import de.srh.toolify.dto.LoginRequest;
import de.srh.toolify.utils.HelperUtil;
import de.srh.toolify.config.ToolifyAuthentication;
import de.srh.toolify.validators.AccessTokenValidator;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;

import java.io.BufferedReader;
import java.io.IOException;

public class AccessTokenValidationFilter extends OncePerRequestFilter {
    private final AntPathMatcher pathMatcher = new AntPathMatcher();
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    ToolifyFailureAuthenticationHandler toolifyFailureAuthenticationHandler;
	@Autowired
	ToolifySuccessAuthenticationHandler toolifySuccessAuthenticationHandler;

    private static String extractToken(HttpServletRequest request) {
        // Extract the token from the Authorization header
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            System.out.println("BEARER AUTH TOKEN RECEIVED !!");
            return authorizationHeader.substring(7);
        }
        return null;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (shouldSkipValidation(request)) {
            // Skip onceperrequest filter for all public and unprotected URLs
            filterChain.doFilter(request, response);
            return;
        }

        try {
            if (isLoginPageRequest(request)) {
                LoginRequest loginRequest = getCredsFromBody(request);
                Authentication authentication = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

                if (HelperUtil.isAuthenticated(authentication)) {
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    //toolifySuccessAuthenticationHandler.onAuthenticationSuccess(request,response, authentication);
                }
            } else {
                Authentication authentication = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(ToolifyAuthentication.getEmail(), ToolifyAuthentication.getPassword()));
                if (HelperUtil.isAuthenticated(authentication)){
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
            filterChain.doFilter(request, response);
        } catch (AuthenticationException ex) {
            toolifyFailureAuthenticationHandler.onAuthenticationFailure(request, response, ex);
        }

         /*String token = extractToken(request);
        if (token != null && AccessTokenValidator.isValidToken(token)) {
            // Token is valid, proceed with the request
            //SecurityContextHolder.getContext().setAuthentication();
            filterChain.doFilter(request, response);
        } else {
            // Token is invalid, return unauthorized response
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.sendRedirect("http://localhost:8081/accessdenied");
        }*/
    }

    private boolean isLoginPageRequest(HttpServletRequest request) {
        return request.getRequestURI().equals("/public/login/user") && request.getMethod().equalsIgnoreCase("POST");
    }

    private boolean shouldSkipValidation(HttpServletRequest request) {
        return ExcludedUrlsConstant.EXCLUDED_URLS.stream().anyMatch(url -> pathMatcher.match(url, request.getRequestURI()));
    }

    private LoginRequest getCredsFromBody(HttpServletRequest request) {
        LoginRequest loginRequest = new LoginRequest();
        try {
            ContentCachingRequestWrapper wrappedRequest = new ContentCachingRequestWrapper(request);
            String requestBody = getRequestPayload(wrappedRequest);


            // Use a JSON library (e.g., Jackson) to parse the JSON payload
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(requestBody.toString());

            String email = jsonNode.get("email").asText();
            String password = jsonNode.get("password").asText();
            loginRequest.setEmail(email);
            loginRequest.setPassword(password);
            ToolifyAuthentication.setEmail(email);
            ToolifyAuthentication.setPassword(password);
            return loginRequest;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String getRequestPayload(HttpServletRequest request) throws IOException {
        StringBuilder payload = new StringBuilder();
        try (BufferedReader reader = request.getReader()) {
            String line;
            while ((line = reader.readLine()) != null) {
                payload.append(line);
            }
        }
        return payload.toString();
    }
}
