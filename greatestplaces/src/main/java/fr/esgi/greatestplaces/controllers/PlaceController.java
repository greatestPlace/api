package fr.esgi.greatestplaces.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;


import fr.esgi.greatestplaces.entities.Place;
import fr.esgi.greatestplaces.entities.User;
import fr.esgi.greatestplaces.repositories.PlaceRepository;
import fr.esgi.greatestplaces.services.AuthService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

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


    @GetMapping(value="/{userId}")
    public List<Place> getPlacesByUserId(@PathVariable Long userId) {
        return this.placeRepository.findAllByUserId(userId);
    }
}
