package fr.esgi.greatestplaces.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;


import fr.esgi.greatestplaces.entities.Place;
import fr.esgi.greatestplaces.entities.User;
import fr.esgi.greatestplaces.repositories.PlaceRepository;
import fr.esgi.greatestplaces.services.AuthService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Objects;

import static org.springframework.http.HttpStatus.*;


@RestController
@RequestMapping("/places")
public class PlaceController {
    private final PlaceRepository placeRepository;
    private final AuthService authService;

    @Autowired
    public PlaceController(PlaceRepository placeRepository, AuthService authService) {
        this.placeRepository = placeRepository;
        this.authService = authService;
    }

    @PostMapping(value="/add")
    public Place addPlace(@RequestBody Place place) {
        User currentUser = authService.getAuthUser();
        if (currentUser == null) {
            throw new ResponseStatusException(NOT_ACCEPTABLE, "User not connected");
        }

        place.setUserId(currentUser.getId());
        return this.placeRepository.save(place);
    }

    @GetMapping(value="/{id}")
    public Place getPlaceById(@PathVariable Long id) {
        Place place = this.placeRepository.findById(id).orElse(null);
        if (place == null) {
            throw new ResponseStatusException(NOT_FOUND, "Place not found");
        }
        return place;
    }


    @GetMapping(value="/userId/{userId}")
    public List<Place> getPlacesByUserId(@PathVariable Long userId) {
        return this.placeRepository.findAllByUserId(userId);
    }

    @GetMapping(value="/{userId}/{name}")
    public List<Place> getPlacesByUserIdAndName(@PathVariable Long userId, @PathVariable String name) {
        return this.placeRepository.findByUserIdAndNameContainsIgnoreCase(userId, name);
    }

    @GetMapping(value="/nameLike/{name}")
    public List<Place> getPlacesByName(@PathVariable String name) {
        return this.placeRepository.findAllByNameContainsIgnoreCase(name);
    }

    @GetMapping(value="/all")
    public List<Place> getAllPlaces() {
        return this.placeRepository.findAll();
    }

    @PutMapping()
    public Place updatePlace(@RequestBody Place place) {
        User currentUser = authService.getAuthUser();
        if (currentUser == null) {
            throw new ResponseStatusException(NOT_ACCEPTABLE, "User not connected");
        }

        if(place.getId() == null) {
            throw new ResponseStatusException(NOT_ACCEPTABLE, "Place id is null");
        }

        Place placeToUpdate = this.placeRepository.findById(place.getId()).orElse(null);
        if (placeToUpdate == null) {
            throw new ResponseStatusException(NOT_FOUND, "Place not found");
        }


        if( !Objects.equals(currentUser.getId(), placeToUpdate.getUserId())) {
            throw new ResponseStatusException(NOT_ACCEPTABLE, "You can't update a place you don't own");
        }

        if (place.getName() != null) {
            placeToUpdate.setName(place.getName());
        }
        if (place.getDescription() != null) {
            placeToUpdate.setDescription(place.getDescription());
        }
        if (place.getLatitude() != null) {
            placeToUpdate.setLatitude(place.getLatitude());
        }
        if (place.getLongitude() != null) {
            placeToUpdate.setLongitude(place.getLongitude());
        }
        if (place.getCity() != null) {
            placeToUpdate.setCity(place.getCity());
        }
        if (place.getCountry() != null) {
            placeToUpdate.setCountry(place.getCountry());
        }
        if (place.getAddress() != null) {
            placeToUpdate.setAddress(place.getAddress());
        }
        if (place.getZipCode() != null) {
            placeToUpdate.setZipCode(place.getZipCode());
        }

        return this.placeRepository.save(placeToUpdate);
    }

    @DeleteMapping(value="/{id}")
    public void deletePlace(@PathVariable Long id) {
        User currentUser = authService.getAuthUser();
        if (currentUser == null) {
            throw new ResponseStatusException(NOT_ACCEPTABLE, "User not connected");
        }

        Place placeToDelete = this.placeRepository.findById(id).orElse(null);
        if (placeToDelete == null) {
            throw new ResponseStatusException(NOT_FOUND, "Place not found");
        }
        if (!Objects.equals(placeToDelete.getUserId(), currentUser.getId())) {
            throw new ResponseStatusException(NOT_ACCEPTABLE, "Not your place");
        }
        this.placeRepository.delete(placeToDelete);
    }
}
