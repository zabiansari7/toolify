package de.srh.toolify.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.srh.toolify.dto.ToolifyResponse;
import de.srh.toolify.entities.AddressEntity;
import de.srh.toolify.exceptions.AddressException;
import de.srh.toolify.services.AddressService;
import de.srh.toolify.validators.ValidatorUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/private/addresses")
@Tag(name = "Address", description = "The Address api to manage user address")
public class AddressController {
	
	@Autowired
    private AddressService addressService;
	
	
	@GetMapping(value = "/address/{addressId}")
    public ResponseEntity<AddressEntity> getAddressById(@PathVariable final Long addressId) {
		AddressEntity address = addressService.getAddressById(addressId);
		return new ResponseEntity<>(address, HttpStatus.OK);
    }
	
	@GetMapping(params = "email")
    public ResponseEntity<List<AddressEntity>> getAddressesByEmail(@PathParam("email") final String email) {
		List<AddressEntity> addresses = addressService.getAddressesByEmail(email);
		return new ResponseEntity<>(addresses, HttpStatus.OK);
    }
	
	@PostMapping("/address")
	 public ResponseEntity<ToolifyResponse> postAddress(@RequestBody final Map<String, Object> address) {
        AddressEntity addressEntity;
        try {
            addressEntity = (AddressEntity) ValidatorUtil.validate(address, AddressEntity.class);
        } catch (Exception e) {
            return new ResponseEntity<>(
                    new ToolifyResponse(
                            String.format(e.getCause().getLocalizedMessage()),
                            400,
                            HttpStatus.BAD_REQUEST
                    ),
                    HttpStatus.BAD_REQUEST);
        }
        Long newAddressId = addressService.saveAddress(addressEntity).getAddressID();
        return new ResponseEntity<>(
                new ToolifyResponse(
                        String.format("New Address with addressId '%d' created successfully.", newAddressId),
                        201,
                        HttpStatus.CREATED
                ),
                HttpStatus.CREATED);
    }
			
	 @DeleteMapping(value = "/{addressId}")
	    public ResponseEntity<ToolifyResponse> deleteAddress(@PathVariable final Long addressId) {
	        try {
	            addressService.deleteAddress(addressId);
	            return new ResponseEntity<>(
	                    new ToolifyResponse(
	                            String.format("Address with addressId '%d' has been deleted successfully.", addressId),
	                            201,
	                            HttpStatus.CREATED
	                    ),
	                    HttpStatus.CREATED);
	        } catch (AddressException e) {
	            return new ResponseEntity<>(
	                    new ToolifyResponse(
	                            e.getMessage(),
	                            400,
	                            HttpStatus.BAD_REQUEST
	                    ),
	                    HttpStatus.BAD_REQUEST);
	        }
	    }
	
	
	
	
	
}
