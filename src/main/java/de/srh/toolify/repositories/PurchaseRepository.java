package de.srh.toolify.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.srh.toolify.entities.PurchasesEntity;
import de.srh.toolify.entities.UserEntity;
import io.swagger.v3.oas.annotations.Hidden;

@Repository
@Hidden
public interface PurchaseRepository extends JpaRepository<PurchasesEntity, Long>{
	Optional<List<PurchasesEntity>> findByUser(UserEntity userEntity);
	Optional<PurchasesEntity> findByInvoice(int invoice);
}
