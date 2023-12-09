package de.srh.toolify.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.srh.toolify.entities.UserEntity;
import io.swagger.v3.oas.annotations.Hidden;

@Repository
@Hidden
public interface UserRepository extends JpaRepository<UserEntity, Long>{
	Optional<UserEntity> findByEmail(String email);
	Optional<UserEntity> findByEmailAndPassword(String email, String password);
}