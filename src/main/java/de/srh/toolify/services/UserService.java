package de.srh.toolify.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.srh.toolify.dto.UserDto;
import de.srh.toolify.entities.UserEntity;
import de.srh.toolify.repositories.UserRepository;


@Service
public class UserService {
	
	private final UserRepository userRepository;
	
	/*
	 * @Autowired PasswordEncoder passwordEncoder;
	 */

    @Autowired
	public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    public UserEntity saveUser(UserEntity user) {
        // Implement any additional logic before adding the user, if needed
    	//user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        return userRepository.save(user);
    }
    
}