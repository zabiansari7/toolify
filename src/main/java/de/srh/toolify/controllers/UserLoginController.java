package de.srh.toolify.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.srh.toolify.dto.LoginRequest;
import de.srh.toolify.dto.LoginResponse;
import de.srh.toolify.entities.UserEntity;
import de.srh.toolify.services.UserDetailsServiceImpl;
import de.srh.toolify.services.UserLoginService;
import de.srh.toolify.utils.RandomGenerator;
import de.srh.toolify.validators.AccessTokenValidator;
import de.srh.toolify.validators.ValidatorUtil;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Validated
@Tag(name = "Login", description = "The User API for login")
@RequestMapping("/public")
public class UserLoginController {
	
	@Autowired
	UserDetailsServiceImpl userDetailsServiceImpl;
	
	@Autowired
    private AuthenticationManager authenticationManager;
	
	@Autowired
	UserLoginService userLoginService;

	@PostMapping("/login/user")
	public ResponseEntity<LoginResponse> login(@RequestBody final Map<String, Object> loginRequest) {
		LoginRequest login = null;
		try {
			login = (LoginRequest) ValidatorUtil.validate(loginRequest, LoginRequest.class);	
		} catch (Exception e) {
			e.printStackTrace();
			//return new ResponseEntity<>(new ToolifyResponse(String.format(e.getMessage()), 400, HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
		}

		UserEntity user;
		Authentication authentication ;
		LoginResponse loginResponse;
		try {
		    userDetailsServiceImpl.loadUserByUsername(login.getEmail());
			authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword()));
			SecurityContextHolder.getContext().setAuthentication(authentication);
			
			if (isAuthenticated(authentication)) {
				user = userLoginService.getUser(login.getEmail());
				loginResponse = new LoginResponse(user.getFirstname(), user.getLastname(), user.getEmail(), user.getHasRole());
				String token = RandomGenerator.generateToken();
				System.out.println("YOUR TOKEN : " + token);
				HttpHeaders responseHeaders = new HttpHeaders();
				responseHeaders.add(HttpHeaders.AUTHORIZATION, token); //Send Token to Vaadin App
				AccessTokenValidator.addValidToken(token); //Add token to Valid Token List
				return new ResponseEntity<>( loginResponse, responseHeaders, HttpStatus.ACCEPTED);
			} else {
				return ResponseEntity.internalServerError().build();
			}
			
		} catch (AuthenticationException e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().build();
		}
	}
	
	@GetMapping("/logout/user")
	public ResponseEntity<?> logout(String emailString){
		String token = AccessTokenValidator.getValidToken();
		AccessTokenValidator.removeToken(token);
		return null;
	}
	
	private boolean isAuthenticated(Authentication authentication) {
	    return authentication != null && authentication.isAuthenticated();
	}
}
