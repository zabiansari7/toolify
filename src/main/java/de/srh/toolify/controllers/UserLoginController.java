package de.srh.toolify.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.srh.toolify.dto.LoginRequest;
import de.srh.toolify.dto.ToolifyResponse;
import de.srh.toolify.services.UserDetailsServiceImpl;
import de.srh.toolify.validators.ValidatorUtil;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Validated
@Tag(name = "Login", description = "The User API for login")
@RequestMapping("/public/api")
public class UserLoginController {
	
	@Autowired
	UserDetailsServiceImpl userDetailsServiceImpl;
	
	@Autowired
    private AuthenticationManager authenticationManager;

	@PostMapping("/login/user")
	public ResponseEntity<ToolifyResponse> login(@RequestBody final Map<String, Object> loginRequest) {
		LoginRequest login;
		try {
			login = (LoginRequest) ValidatorUtil.validate(loginRequest, LoginRequest.class);	
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new ToolifyResponse(String.format(e.getMessage()), 400, HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
		}
		UserDetails user;
		try {
			user = userDetailsServiceImpl.loadUserByUsername(login.getEmail());
			Authentication authentication = authenticationManager.authenticate(
				    new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword())
			);
			if (authentication.isAuthenticated()) {
				return new ResponseEntity<>( new ToolifyResponse("SUCCESS", 202, HttpStatus.ACCEPTED), HttpStatus.ACCEPTED);
			} else {
				return new ResponseEntity<>( new ToolifyResponse("UNAUTHORIZED", 401, HttpStatus.UNAUTHORIZED), HttpStatus.UNAUTHORIZED);
			}
			
		} catch (AuthenticationException e) {
			e.printStackTrace();
			return new ResponseEntity<>( new ToolifyResponse(e.getMessage(), 401, HttpStatus.UNAUTHORIZED), HttpStatus.UNAUTHORIZED);
		}
		
	}
}
