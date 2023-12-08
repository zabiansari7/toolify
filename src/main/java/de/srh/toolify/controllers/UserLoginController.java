package de.srh.toolify.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.srh.toolify.dto.LoginRequest;
import de.srh.toolify.dto.ToolifyResponse;
import de.srh.toolify.services.UserLoginService;
import de.srh.toolify.validators.ValidatorUtil;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Validated
@Tag(name = "Login", description = "The User API for login")
@RequestMapping("/public/api")
public class UserLoginController {
	
	@Autowired
	UserLoginService userLoginService;

	@PostMapping("/login/user")
	public ResponseEntity<ToolifyResponse> login(@RequestBody final Map<String, Object> loginRequest) {
		LoginRequest login;
		try {
			login = (LoginRequest) ValidatorUtil.validate(loginRequest, LoginRequest.class);
			if (userLoginService.authenticate(login)) {
				return new ResponseEntity<>(new ToolifyResponse(String.format("User with email address '%s' is Logged In", login.getEmail()), 200, HttpStatus.OK), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(new ToolifyResponse(String.format("Wrong email and password. Try with right combination"), 500, HttpStatus.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new ToolifyResponse(String.format(e.getMessage()), 400, HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
		}
	}
}
