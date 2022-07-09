package fr.esgi.greatestplaces.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import fr.esgi.greatestplaces.entities.Place;
import java.util.Collection;

public interface PlaceRepository extends JpaRepository<Place, Long> {
}
