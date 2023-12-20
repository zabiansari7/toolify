package de.srh.toolify.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import de.srh.toolify.validators.AccessTokenValidator;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class AccessTokenValidationFilter extends OncePerRequestFilter {
	private final AntPathMatcher pathMatcher = new AntPathMatcher();

	public static boolean isIncomingTokenValid(HttpServletRequest request, HttpServletResponse response) {
		String token = extractToken(request);

		if (token != null && AccessTokenValidator.isValidToken(token) && SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
			return true;
		} else {
			// Token is invalid, return unauthorized response
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return false;
		}

	}

	private static String extractToken(HttpServletRequest request) {
		// Extract the token from the Authorization header
		String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
			System.out.println("BEARER AUTH TOKEN RECEIVED !!");
			System.out.println(authorizationHeader.substring(7));
			return authorizationHeader.substring(7);
		}
		return null;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		if (shouldSkipValidation(request)) {
			// Skip token validation for specific URLs
			filterChain.doFilter(request, response);
			return;
		}
		String token = extractToken(request);
		if (token != null && AccessTokenValidator.isValidToken(token)) {
			// Token is valid, proceed with the request
			filterChain.doFilter(request, response);
		} else {
			// Token is invalid, return unauthorized response
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			response.sendRedirect("http://localhost:8081/accessdenied");
		}
	}
	private boolean shouldSkipValidation(HttpServletRequest request) {
		return ExcludedUrlsConstant.EXCLUDED_URLS.stream().anyMatch(url -> pathMatcher.match(url, request.getRequestURI()));
	}
}
