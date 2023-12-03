package de.srh.toolify.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import de.srh.toolify.entities.PurchasesEntity;

public interface PurchasesRepository extends JpaRepository<PurchasesEntity, Long>{

}
