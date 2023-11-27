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
    
    public UserEntity saveUser(UserDto userDto) {
        // Implement any additional logic before adding the user, if needed
    	//user.setPassword(passwordEncoder.encode(userDto.getPassword()));
    	UserEntity user = new UserEntity();
    	user.setEmail(userDto.getEmail());
    	user.setPassword(userDto.getPassword());
    	user.setFirstname(userDto.getFirstname());
    	user.setLastname(userDto.getLastname());
    	user.setIsAdmin(userDto.getIsAdmin());
        return userRepository.save(user);
    }
    
}