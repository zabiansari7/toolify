package de.srh.toolify.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.srh.toolify.entities.ProductEntity;
import io.swagger.v3.oas.annotations.Hidden;

@Repository
@Hidden
public interface ProductRepository extends JpaRepository<ProductEntity, Long>{
	
	/*
	 * @Override
	 * 
	 * @Query("SELECT p FROM ProductEntity p") List<ProductEntity> findAll();
	 */
}