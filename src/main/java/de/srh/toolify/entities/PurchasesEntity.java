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
	
	@ManyToOne
    @JoinColumn(name = "purchaseItemsId")
    private PurchaseItemsEntity purchaseItemsEntity;

}