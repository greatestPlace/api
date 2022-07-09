package fr.esgi.greatestplaces.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import fr.esgi.greatestplaces.entities.AuthEntity;


public interface AuthRepository extends JpaRepository<AuthEntity, Long> {

    public AuthEntity getByUsername(String username);
}
