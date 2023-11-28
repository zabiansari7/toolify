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

import de.srh.toolify.entities.UserEntity;
import de.srh.toolify.services.UserService;
import de.srh.toolify.validators.ValidatorUtil;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Validated
@Tag(name = "Users", description = "The User APIs for registration")
@RequestMapping("/api/users")
public class UserRegistrationController {
	
	private final UserService userService;
	
	@Autowired
	public UserRegistrationController(UserService userService) {
		this.userService = userService;
	}
	
	@PostMapping
	public ResponseEntity<UserEntity> registerUser(@RequestBody Map<String, Object> user){
		UserEntity userEntity;
		try {
			userEntity = (UserEntity) ValidatorUtil.validate(user, UserEntity.class);
		} catch (Exception e) {
			// TODO: handle exception
		}
		UserEntity addedUser = userService.saveUser(userEntity);
		return new ResponseEntity<>(addedUser, HttpStatus.CREATED);
	}	
	
}
