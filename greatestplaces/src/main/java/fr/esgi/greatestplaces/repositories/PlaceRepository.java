package fr.esgi.greatestplaces.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import fr.esgi.greatestplaces.entities.Place;

public interface PlaceRepository extends JpaRepository<Place, Long> {
    Place getPostById(Long id);
    
    List<Place> findAllByName(String name);

    List<Place> findAllByUserId(Long userId);

    List<Place> findAllByNameLike(String name);

    List<Place> findAllByUserIdAndNameLikeIgnoreCase(Long userId, String name);
}
