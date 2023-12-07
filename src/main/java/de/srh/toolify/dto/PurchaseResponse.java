package de.srh.toolify.dto;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

import de.srh.toolify.entities.PurchaseItemsEntity;

public class PurchaseResponse {
	
	private String message;
	private Instant date;
	private BigDecimal totalPrice;
	private List<PurchaseItemsEntity> purchaseItemsEntities;
	
	public PurchaseResponse(String message, Instant date, BigDecimal totalPrice, List<PurchaseItemsEntity> purchaseItemsEntities) {
		this.message = message;
		this.date = date;
		this.totalPrice = totalPrice;
		this.purchaseItemsEntities = purchaseItemsEntities;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
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
