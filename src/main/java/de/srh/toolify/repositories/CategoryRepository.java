package de.srh.toolify.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.srh.toolify.entities.CategoryEntity;
import io.swagger.v3.oas.annotations.Hidden;

@Repository
@Hidden
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long>{
	Optional<CategoryEntity> findByCategoryName(String categoryName);
}
