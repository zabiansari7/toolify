package de.srh.toolify.services;

import java.time.Instant;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import de.srh.toolify.entities.UserEntity;
import de.srh.toolify.exceptions.UserException;
import de.srh.toolify.repositories.UserRepository;

@Service
public class UserRegistrationService {

	private final UserRepository userRepository;
	 
	@Autowired 
	PasswordEncoder passwordEncoder;
	
	@Autowired
	ModelMapper mapper;

	@Autowired
	public UserRegistrationService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public String saveUser(UserEntity user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setHasRole("DEFAULT");
		user.setCreatedOn(Instant.now());
		try {
			return userRepository.saveAndFlush(user).getEmail();
		} catch (Exception e) {
			throw new UserException(e.getCause().getLocalizedMessage(), e);
		}
	}
	
	public String updateUserByEmail(Map<String, Object> userProps, String email) {
		UserEntity existingUser = userRepository.findByEmail(email).orElseThrow(() -> new UserException(String.format("User with email '%s' not found", email), null));
		existingUser.setUpdatedOn(Instant.now());
		mapper.map(userProps, existingUser);
		return userRepository.saveAndFlush(existingUser).getEmail();
	}
	
	public UserEntity getUserByEmail(String email) {
		return userRepository.findByEmail(email).orElseThrow(() -> new UserException(String.format("User with email address '%s' not found.", email), null));
	}

}