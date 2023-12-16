package de.srh.toolify.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.srh.toolify.entities.ProductEntity;
import de.srh.toolify.entities.UserEntity;
import de.srh.toolify.services.UserRegistrationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.websocket.server.PathParam;

@RestController
@Validated
@Tag(name = "User", description = "The User API")
@RequestMapping("/private")
public class UserController {
	
	@Autowired
	UserRegistrationService registrationService;

	@GetMapping(value = "/user", params = "email")
	public ResponseEntity<UserEntity> getProductByProductId(@PathParam("email") final String email){
		try {
			UserEntity user = registrationService.getUserByEmail(email);
			return new ResponseEntity<>(user, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
