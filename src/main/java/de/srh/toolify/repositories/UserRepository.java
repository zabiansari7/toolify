package de.srh.toolify.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.srh.toolify.entities.UserEntity;



@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>{

}