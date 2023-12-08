package de.srh.toolify.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import de.srh.toolify.dto.LoginRequest;
import de.srh.toolify.entities.UserEntity;
import de.srh.toolify.exceptions.UserException;
import de.srh.toolify.repositories.UserLoginRepository;

@Service
public class UserLoginService {
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	private final UserLoginRepository userLoginRepository;
	
	public UserLoginService(UserLoginRepository userLoginRepository) {
		this.userLoginRepository = userLoginRepository;
	}



	public boolean authenticate(LoginRequest loginRequest) {
		String encodedPassword = passwordEncoder.encode(loginRequest.getPassword());
		System.out.println(encodedPassword);
		UserEntity loggedInUser = userLoginRepository.findByEmailAndPassword(loginRequest.getEmail(), encodedPassword).orElseThrow(() -> new UserException(String.format("User with email address '%s' not found.",loginRequest.getEmail()), null));
		return true;
	}
	
}
