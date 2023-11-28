package de.srh.toolify.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import de.srh.toolify.dto.UserDto;
import de.srh.toolify.entities.UserEntity;
import de.srh.toolify.services.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Validated
@Tag(name = "Users", description = "The User APIs for registration")
@RequestMapping("/api/users")
public class UserController {
	
	private final UserService userService;
	private final ObjectMapper mapper;
	
	@Autowired
	public UserController(UserService userService, ObjectMapper mapper) {
		this.userService = userService;
		this.mapper = mapper;
	}
	
	@PostMapping
	public ResponseEntity<UserEntity> addUser(@RequestBody UserDto userDto){
		UserEntity addedUser = userService.saveUser(userDto);
		return new ResponseEntity<>(addedUser, HttpStatus.CREATED);
	}	
	
}
