package de.srh.toolify.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.srh.toolify.entities.PurchaseItemsEntity;
import de.srh.toolify.entities.PurchasesEntity;
import io.swagger.v3.oas.annotations.Hidden;

@Repository
@Hidden
public interface PurchaseItemsRepository extends JpaRepository<PurchaseItemsEntity, Long>{
	Optional<List<PurchaseItemsEntity>> findByPurchase(PurchasesEntity purchase);
}
