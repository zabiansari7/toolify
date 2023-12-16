package de.srh.toolify.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.srh.toolify.dto.PurchaseResponse;
import de.srh.toolify.entities.PurchaseItemsEntity;
import de.srh.toolify.entities.PurchasesEntity;
import de.srh.toolify.entities.UserEntity;
import de.srh.toolify.exceptions.PurchaseException;
import de.srh.toolify.exceptions.PurchaseItemsException;
import de.srh.toolify.repositories.PurchaseItemsRepository;
import de.srh.toolify.repositories.PurchaseRepository;

@Service
public class PurchaseHistoryService {
	
	private final PurchaseRepository purchaseRepository;
	private final PurchaseItemsRepository purchaseItemsRepository;
	
	@Autowired
	UserRegistrationService userRegistrationService;
	
	public PurchaseHistoryService(PurchaseRepository purchaseRepository, PurchaseItemsRepository purchaseItemsRepository) {
		this.purchaseRepository = purchaseRepository;
		this.purchaseItemsRepository = purchaseItemsRepository;
	}
	
	public List<PurchaseResponse> getAllPurchaseHistory() {
		List<PurchasesEntity> purchases = purchaseRepository.findAll();
		List<PurchaseResponse> responses = new ArrayList<>();
		
		for (PurchasesEntity purchase : purchases) {
			PurchaseResponse response = new PurchaseResponse();
			response.setUser(purchase.getUser());
			response.setDate(purchase.getDate());
			response.setTotalPrice(purchase.getTotalPrice());
			List<PurchaseItemsEntity> purchaseItems = purchaseItemsRepository.findByPurchase(purchase).orElseThrow(() -> new PurchaseItemsException(String.format("Purchase Item with purchaseId '%d' could not be found", purchase.getPurchaseId()), null));
			response.setPurchaseItemsEntities(purchaseItems);
			responses.add(response);
		}
		return responses;
	}
	
	public List<PurchaseResponse> getAllPurchaseHistoryByEmail(String email) {
		UserEntity user = userRegistrationService.getUserByEmail(email);
		List<PurchaseResponse> responses = new ArrayList<>();
		List<PurchasesEntity> purchases = purchaseRepository.findByUser(user).orElseThrow(() -> new PurchaseItemsException(String.format("Purchase with email add '%s' not found", email), null));
		
		for (PurchasesEntity purchase : purchases) {
			PurchaseResponse response = new PurchaseResponse();
			response.setUser(purchase.getUser());
			response.setDate(purchase.getDate());
			response.setTotalPrice(purchase.getTotalPrice());
			response.setInvoice(purchase.getInvoice());
			List<PurchaseItemsEntity> purchaseItems = purchaseItemsRepository.findByPurchase(purchase).orElseThrow(() -> new PurchaseItemsException(String.format("Purchase Item with purchaseId '%d' could not be found", purchase.getPurchaseId()), null));
			response.setPurchaseItemsEntities(purchaseItems);
			responses.add(response);
		}
		return responses;
	}
	
	public PurchaseResponse getPurchaseHistoryByInvoice(int invoice) {
		PurchasesEntity purchase = purchaseRepository.findByInvoice(invoice).orElseThrow(() -> new PurchaseException(String.format("Purchase history not found for invoice with id '%d'", invoice), null));
		List<PurchaseItemsEntity> purchaseItems = purchaseItemsRepository.findByPurchase(purchase).orElseThrow(() -> new PurchaseItemsException(String.format("Purchase Item with purchaseId '%d' could not be found", purchase.getPurchaseId()), null));
		PurchaseResponse response = new PurchaseResponse();
		response.setPurchaseId(Integer.valueOf(purchase.getPurchaseId().toString()));
		response.setInvoice(invoice);
		response.setDate(purchase.getDate());
		response.setTotalPrice(purchase.getTotalPrice());
		response.setUser(purchase.getUser());
		response.setPurchaseItemsEntities(purchaseItems);
		return response;
	}
	

}
