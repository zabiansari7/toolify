package de.srh.toolify.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.srh.toolify.dto.ToolifyResponse;
import de.srh.toolify.entities.ProductEntity;
import de.srh.toolify.entities.UserEntity;
import de.srh.toolify.services.UserRegistrationService;
import de.srh.toolify.validators.UserPropsValidator;
import de.srh.toolify.validators.ValidatorUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.websocket.server.PathParam;

@RestController
@Validated
@Tag(name = "User", description = "The User API")
@RequestMapping("/private")
public class UserController {
	
	@Autowired
	UserRegistrationService userRegistrationService;

	@GetMapping(value = "/user", params = "email")
	public ResponseEntity<UserEntity> getProductByProductId(@PathParam("email") final String email){
		try {
			UserEntity user = userRegistrationService.getUserByEmail(email);
			return new ResponseEntity<>(user, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
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
