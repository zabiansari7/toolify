package de.srh.toolify.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.srh.toolify.entities.AddressEntity;
import de.srh.toolify.entities.UserEntity;
import io.swagger.v3.oas.annotations.Hidden;

@Repository
@Hidden
public interface AddressRepository extends JpaRepository<AddressEntity, Long> {
	 Optional<List<AddressEntity>> findByUser(UserEntity userEntity);
}
