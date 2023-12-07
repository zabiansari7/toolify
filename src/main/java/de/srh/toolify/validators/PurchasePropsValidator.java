package de.srh.toolify.validators;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;


public class PurchasePropsValidator {
	
	private String email;
	private List<PurchaseItemsPropsValidator> purchaseItems;
	
	@JsonCreator
	public PurchasePropsValidator(@JsonProperty("email") String email, @JsonProperty("purchaseItems") List<PurchaseItemsPropsValidator> purchaseItems) {
		this.email = email;
		this.purchaseItems = purchaseItems;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<PurchaseItemsPropsValidator> getPurchaseItems() {
		return purchaseItems;
	}
	public void setPurchaseItems(List<PurchaseItemsPropsValidator> purchaseItems) {
		this.purchaseItems = purchaseItems;
	}
	
	

}
