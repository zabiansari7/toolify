package de.srh.toolify.validators;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PurchaseItemsPropsValidator {
	
	private int quantity;
	private Long productId;
	
	@JsonCreator
	public PurchaseItemsPropsValidator(@JsonProperty("quantity") int quantity, @JsonProperty("productId") Long productId) {
		this.quantity = quantity;
		this.productId = productId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	
	

}
