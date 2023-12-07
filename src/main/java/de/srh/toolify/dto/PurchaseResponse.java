package de.srh.toolify.dto;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

import de.srh.toolify.entities.PurchaseItemsEntity;
import de.srh.toolify.entities.UserEntity;

public class PurchaseResponse {
	
	private UserEntity user;
	private Instant date;
	private BigDecimal totalPrice;
	private List<PurchaseItemsEntity> purchaseItemsEntities;
	
	public UserEntity getUser() {
		return user;
	}
	
	public void setUser(UserEntity user) {
		this.user = user;
	}
	
	public Instant getDate() {
		return date;
	}
	
	public void setDate(Instant date) {
		this.date = date;
	}
	
	public BigDecimal getTotalPrice() {
		return totalPrice;
	}
	
	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	public List<PurchaseItemsEntity> getPurchaseItemsEntities() {
		return purchaseItemsEntities;
	}
	
	public void setPurchaseItemsEntities(List<PurchaseItemsEntity> purchaseItemsEntities) {
		this.purchaseItemsEntities = purchaseItemsEntities;
	}
	
}
