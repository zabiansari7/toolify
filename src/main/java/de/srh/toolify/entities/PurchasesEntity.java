package de.srh.toolify.entities;

import java.math.BigDecimal;
import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "purchases")
public class PurchasesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "purchaseId", columnDefinition = "int")
    private Long purchaseId;

    @Column(name = "date", columnDefinition = "datetime", nullable = false)
    private Instant date;

    @Column(name =  "totalPrice", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalPrice;

    @ManyToOne
    @JoinColumn(name = "userId")
    private UserEntity user;
    
    @Column(name = "invoice", columnDefinition = "int", nullable = false)
    private int invoice;
    

	public Long getPurchaseId() {
		return purchaseId;
	}

	public void setPurchaseId(Long purchaseId) {
		this.purchaseId = purchaseId;
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

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public int getInvoice() {
		return invoice;
	}

	public void setInvoice(int invoice) {
		this.invoice = invoice;
	}

	@Override
	public String toString() {
		return "PurchasesEntity [purchaseId=" + purchaseId + ", date=" + date + ", totalPrice=" + totalPrice + ", user="
				+ user + "]";
	}

}