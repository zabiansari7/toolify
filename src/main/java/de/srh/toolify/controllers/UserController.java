package de.srh.toolify.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.srh.toolify.dto.UserDto;
import de.srh.toolify.entities.UserEntity;
import de.srh.toolify.services.UserService;

@RestController
@RequestMapping("api/users")
public class UserController {
	
	private final UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@PostMapping
	public ResponseEntity<UserEntity> addUser(@RequestBody UserDto userDto){
		UserEntity addedUser = userService.saveUser(userDto);
		return new ResponseEntity<>(addedUser, HttpStatus.CREATED);
	}	
	
}
