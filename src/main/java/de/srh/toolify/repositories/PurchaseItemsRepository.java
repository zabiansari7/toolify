package de.srh.toolify.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import de.srh.toolify.entities.PurchaseItemsEntity;

public interface PurchaseItemsRepository extends JpaRepository<PurchaseItemsEntity, Long>{

}
