package de.srh.toolify.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.srh.toolify.entities.UserEntity;

@Service
public class UserLoginService {
	
	@Autowired
	UserRegistrationService userRegistrationService;
	
	public UserEntity getUser(String email) {
		return userRegistrationService.getUserByEmail(email);
	}
}
