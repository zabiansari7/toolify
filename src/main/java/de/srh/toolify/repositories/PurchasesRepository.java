package de.srh.toolify.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.srh.toolify.entities.PurchasesEntity;
import io.swagger.v3.oas.annotations.Hidden;

@Repository
@Hidden
public interface PurchasesRepository extends JpaRepository<PurchasesEntity, Long>{

}
