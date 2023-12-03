package de.srh.toolify.entities;

import java.math.BigDecimal;

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

}
