package de.srh.toolify.entities;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="purchaseItems")
public class PurchaseItemsEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "purchaseItemsId", columnDefinition = "int")
	private Long purchaseItemsId;
	
	@Column(name = "quantity", columnDefinition = "int")
	private int quantity;
	
	@Column(name = "purchasePrice", precision = 10, scale = 2)
	private BigDecimal purchasePrice;

    @ManyToOne
    @JoinColumn(name = "productId")
    private ProductEntity productEntity;
    
    @ManyToOne
    @JoinColumn(name = "purchaseId")
    @JsonIgnore
    private PurchasesEntity purchase;


	public Long getPurchaseItemsId() {
		return purchaseItemsId;
	}

	public void setPurchaseItemsId(Long purchaseItemsId) {
		this.purchaseItemsId = purchaseItemsId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(BigDecimal purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public ProductEntity getProductEntity() {
		return productEntity;
	}

	public void setProductEntity(ProductEntity productEntity) {
		this.productEntity = productEntity;
	}

	public PurchasesEntity getPurchase() {
		return purchase;
	}

	public void setPurchase(PurchasesEntity purchase) {
		this.purchase = purchase;
	}  

}
