package de.srh.toolify.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.srh.toolify.entities.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>{
	public Optional<UserEntity> findByEmail(String email);
	public Optional<UserEntity> findByEmailAndPassword(String email, String password);
}