package de.srh.toolify.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.srh.toolify.dto.ToolifyResponse;
import de.srh.toolify.entities.UserEntity;
import de.srh.toolify.services.UserRegistrationService;
import de.srh.toolify.validators.UserPropsValidator;
import de.srh.toolify.validators.ValidatorUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.websocket.server.PathParam;

@RestController
@Validated
@Tag(name = "Users", description = "The User APIs for registration")
@RequestMapping("/public/api/users")
public class UserRegistrationController {
	
	private final UserRegistrationService userRegistrationService;
	
	@Autowired
	public UserRegistrationController(UserRegistrationService userService) {
		this.userRegistrationService = userService;
	}
	
	@PostMapping(value = "/user")
	public ResponseEntity<ToolifyResponse> registerUser(@RequestBody final Map<String, Object> user){
		UserEntity userEntity;
		try {
			userEntity = (UserEntity) ValidatorUtil.validate(user, UserEntity.class);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new ToolifyResponse(String.format(e.getMessage()), 400, HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
		}
		String storedEmail = userRegistrationService.saveUser(userEntity);
		return new ResponseEntity<>(new ToolifyResponse(String.format("New User with email '%s' created successfully", storedEmail), 201, HttpStatus.CREATED), HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/user", params = "email")
	public ResponseEntity<ToolifyResponse> editUserByEmail(@RequestBody final Map<String, Object> userProps, @PathParam(value = "email") final String email) {
		try {
			ValidatorUtil.validate(userProps, UserPropsValidator.class);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new ToolifyResponse(String.format(e.getMessage()), 400, HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
		}
		String updatedUserEmail = userRegistrationService.updateUserByEmail(userProps, email);
		return new ResponseEntity<>(new ToolifyResponse(String.format("User with email '%s' updated successfully", updatedUserEmail), 201, HttpStatus.CREATED), HttpStatus.CREATED);
	}
	
}
