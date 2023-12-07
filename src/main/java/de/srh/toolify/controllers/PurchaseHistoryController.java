package de.srh.toolify.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.srh.toolify.dto.PurchaseResponse;
import de.srh.toolify.services.PurchaseHistoryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.websocket.server.PathParam;

@RestController
@Validated
@Tag(name = "PurchaseHistory", description = "The Purchase History APIs for a user email and also for all users for administration purposes")
@RequestMapping("/private")
public class PurchaseHistoryController {
	
	@Autowired
	PurchaseHistoryService purchaseHistoryService;
	
	@GetMapping("/admin/purchases/history/all")
	public ResponseEntity<List<PurchaseResponse>> getPurchaseHistoryAllUsers(){
		List<PurchaseResponse> purchases = purchaseHistoryService.getAllPurchaseHistory();
		return new ResponseEntity<>(purchases, HttpStatus.ACCEPTED);
	}
	
	@GetMapping(value = "purchases/history", params = "email")
	public ResponseEntity<List<PurchaseResponse>> getPurchaseHistoryByEmail(@PathParam(value = "email") final String email){
		List<PurchaseResponse> purchases = purchaseHistoryService.getAllPurchaseHistoryByEmail(email);
		return new ResponseEntity<>(purchases, HttpStatus.ACCEPTED);
	}

}
