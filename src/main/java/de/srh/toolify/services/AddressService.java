package de.srh.toolify.services;

import java.time.Instant;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.srh.toolify.entities.AddressEntity;
import de.srh.toolify.entities.UserEntity;
import de.srh.toolify.exceptions.AddressException;
import de.srh.toolify.repositories.AddressRepository;
import jakarta.validation.constraints.Email;

@Service
public class AddressService {
	@Autowired
	private AddressRepository addressRepository;
	@Autowired
	private UserRegistrationService userRegistrationService;

	public AddressEntity saveAddress(AddressEntity address) {
		address.setCreatedOn(Instant.now());
		String email = address.getUser().getEmail();
		UserEntity user = userRegistrationService.getUserByEmail(email);
		address.setUser(user);
		return addressRepository.save(address);
	}

	 public void deleteAddress(Long addressId) {
	        AddressEntity existingAddress = getAddressById(addressId).orElse(null);

	        if (existingAddress != null) {
	            try {
	                addressRepository.delete(existingAddress);
	            } catch (Exception e) {
	                throw new AddressException("Error deleting address with addressId: " + addressId, e);
	            }
	        } else {
	            throw new AddressException("Address not found with addressId: " + addressId, null);
	        }
	    }
	
	public Optional<AddressEntity> getAddressById(Long id) {
        return addressRepository.findById(id);
    }
	
	
	
}	