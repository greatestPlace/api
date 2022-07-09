package fr.esgi.greatestplaces.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import fr.esgi.greatestplaces.entities.Place;

public interface PlaceRepository extends JpaRepository<Place, Long> {
    List<Place> findAllByUserId(String userId);
    List<Place> findAllByNameLike(String name);
    void deletePostById(Long id);
}
