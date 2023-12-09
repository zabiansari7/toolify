package de.srh.toolify.services;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.srh.toolify.entities.AddressEntity;
import de.srh.toolify.entities.UserEntity;
import de.srh.toolify.exceptions.AddressException;
import de.srh.toolify.repositories.AddressRepository;

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
		 AddressEntity existingAddress = addressRepository.findById(addressId).orElseThrow(() -> new AddressException(String.format("Address not found with addressId: '%d'", addressId), null));
		 addressRepository.delete(existingAddress);
	 }
	
	public AddressEntity getAddressById(Long id) {
         AddressEntity addressEntity = addressRepository.findById(id).orElseThrow(() -> new AddressException(String.format("Address not found with addressId: '%d'", id), null));
         return addressEntity;
    }

	public List<AddressEntity> getAddressesByEmail(String email) {
		UserEntity user = userRegistrationService.getUserByEmail(email);
		List<AddressEntity> addresses = addressRepository.findByUser(user).orElseThrow(() -> new AddressException(String.format("Address not found for user with email address '%s'", email), null));
		return addresses;
	}
	
	
	
}	