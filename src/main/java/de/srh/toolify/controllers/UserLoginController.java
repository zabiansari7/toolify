package de.srh.toolify.controllers;

import de.srh.toolify.services.UserRegistrationService;
import de.srh.toolify.utils.HelperUtil;
import de.srh.toolify.utils.RandomGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.srh.toolify.dto.LoginResponse;
import de.srh.toolify.entities.UserEntity;
import de.srh.toolify.validators.AccessTokenValidator;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Validated
@Tag(name = "Login", description = "The User API for login")
@RequestMapping("/public")
public class UserLoginController {
	@Autowired
	UserRegistrationService userRegistrationService;

	@PostMapping("/login/user")
	public ResponseEntity<LoginResponse> login() {
		Authentication authentication =SecurityContextHolder.getContext().getAuthentication();
		if (HelperUtil.isAuthenticated(authentication)) {
			LoginResponse response = prepareLoginResponse(authentication.getName());
			String token = RandomGenerator.generateToken();
			System.out.println("YOUR TOKEN : " + token);
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.add(HttpHeaders.AUTHORIZATION, token); //Send Token to Vaadin App
			AccessTokenValidator.addValidToken(token); //Add token to Valid Token List
			return new ResponseEntity<>( response, responseHeaders, HttpStatus.ACCEPTED);
		}
		return null;
	}
	
	@GetMapping("/logout/user")
	public ResponseEntity<?> logout(String emailString){
		String token = AccessTokenValidator.getValidToken();
		AccessTokenValidator.removeToken(token);
		return null;
	}

	private LoginResponse prepareLoginResponse(String email) {
		UserEntity user = userRegistrationService.getUserByEmail(email);
		LoginResponse loginResponse = new LoginResponse(user.getFirstname(), user.getLastname(), user.getEmail(), user.getHasRole());
		return loginResponse;
	}
}
