package fr.esgi.greatestplaces.repositories;

import java.util.List;
import java.util.stream.Collectors;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import fr.esgi.greatestplaces.entities.Place;

public interface PlaceRepository extends JpaRepository<Place, Long> {
    Place getPostById(Long id);
    
    List<Place> findAllByName(String name);

    List<Place> findAllByUserId(Long userId);

    List<Place> findAllByNameContainsIgnoreCase(@Param("name") String name);

    List<Place> findByUserIdAndNameContainsIgnoreCase(@Param("user_id") Long userId,@Param("name") String name);

    List<Place> findAll();
}
