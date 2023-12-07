package de.srh.toolify.services;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import de.srh.toolify.entities.ProductEntity;
import de.srh.toolify.entities.PurchaseItemsEntity;
import de.srh.toolify.entities.PurchasesEntity;
import de.srh.toolify.entities.UserEntity;
import de.srh.toolify.exceptions.ProductException;
import de.srh.toolify.exceptions.PurchaseException;
import de.srh.toolify.exceptions.UserException;
import de.srh.toolify.repositories.ProductRepository;
import de.srh.toolify.repositories.PurchaseItemsRepository;
import de.srh.toolify.repositories.PurchaseRepository;
import de.srh.toolify.repositories.UserRepository;
import de.srh.toolify.validators.PurchaseItemsPropsValidator;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class PurchaseService {
	
	private final PurchaseRepository purchaseRepository;
	private final ProductRepository productRepository;
	private final UserRepository userRepository;
	private final PurchaseItemsRepository purchaseItemsRepository;
	
	@Autowired
	ModelMapper mapper;
	
	@Autowired
    private JdbcTemplate jdbcTemplate;

	@Autowired
	public PurchaseService(PurchaseRepository purchaseRepository, ProductRepository productRepository, UserRepository userRepository, PurchaseItemsRepository purchaseItemsRepository) {
		this.purchaseRepository = purchaseRepository;
		this.productRepository = productRepository;
		this.userRepository = userRepository;
		this.purchaseItemsRepository = purchaseItemsRepository;
	}

	// On Cart Page just do the calculation on the frontend for showing purpose only but in reality actual calculation will happen here in business logic code.
	@SuppressWarnings("unchecked")
	@Transactional
	public PurchasesEntity purchase(Map<String, Object> purchaseProps) { // do not rely on frontend to send calculated total price of purchase items instead just use the qty and get product price from productId and multiply both. Generate total price here.
		String loggedInEmail = purchaseProps.get("email").toString();
		UserEntity loggedInUser = userRepository.findByEmail(loggedInEmail).orElseThrow(() -> new UserException(String.format("User with email address '%s' not found.", loggedInEmail), null));
		
		PurchasesEntity purchase = new PurchasesEntity();
		List<PurchaseItemsEntity> purchaseItemsEntities = new ArrayList<>();
		BigDecimal totalPrice = BigDecimal.valueOf(0);
		
		List<PurchaseItemsPropsValidator> purchaseItems = (List<PurchaseItemsPropsValidator>) purchaseProps.get("purchaseItems");
		JSONArray purchaseItemsArray = new JSONArray(purchaseItems);
		
		for (Object purchaseItemObject : purchaseItemsArray) {
			JSONObject  singleItem = (JSONObject) purchaseItemObject;
			PurchaseItemsEntity purchaseItem = preparePurchaseItems(singleItem);
			purchaseItemsEntities.add(purchaseItem);
			totalPrice = totalPrice.add(purchaseItem.getPurchasePrice());
		}

		purchase.setUser(loggedInUser);
		purchase.setDate(Instant.now());
		purchase.setTotalPrice(totalPrice);
		
		try {
			purchaseRepository.saveAndFlush(purchase);
			
			for (PurchaseItemsEntity purchaseItemsEntity : purchaseItemsEntities) {
				purchaseItemsEntity.setPurchase(purchase);
				purchaseItemsRepository.saveAndFlush(purchaseItemsEntity);
				
				jdbcTemplate.update("CALL productQtyTrigger(?, ?)", purchaseItemsEntity.getProductEntity().getProductId(), purchaseItemsEntity.getQuantity());
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new PurchaseException("Error in saving purchase. " + e.getMessage(), e);
		}

		return purchase;
	}
	
	private PurchaseItemsEntity preparePurchaseItems(JSONObject purchaseItemsProps) {
		PurchaseItemsEntity purchaseItemsEntity = new PurchaseItemsEntity();
		Long productId = Long.valueOf(purchaseItemsProps.getInt("productId"));
		ProductEntity product = productRepository.findById(productId).orElseThrow(() -> new ProductException(String.format("Product with productId '%d' not found.", productId), null));
		purchaseItemsEntity.setProductEntity(product);
		
		BigDecimal perPrice = product.getPrice();
		int quantity = purchaseItemsProps.getInt("quantity");
		purchaseItemsEntity.setQuantity(quantity);
		
		BigDecimal purchasePrice = perPrice.multiply(BigDecimal.valueOf(quantity));
		purchaseItemsEntity.setPurchasePrice(purchasePrice);
		
		try {
			return purchaseItemsEntity;
		} catch (Exception e) {
			e.printStackTrace();	
			throw new PurchaseException(String.format("Problem in saving the purchase item with product id ''", product.getProductId()), e);
		}
	}
	
	

}
