package fr.esgi.greatestplaces.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import fr.esgi.greatestplaces.entities.AuthEntity;
import fr.esgi.greatestplaces.entities.User;
import fr.esgi.greatestplaces.repositories.AuthRepository;
import fr.esgi.greatestplaces.repositories.UserRepository;
import fr.esgi.greatestplaces.services.AuthService;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("users")
public class UserController {

    private UserRepository userRepo;
    private AuthRepository authRepository;
    private AuthService authService;
    @Autowired
    public UserController(UserRepository userRepo, AuthRepository authRepository, AuthService authService) {
        this.userRepo = userRepo;
        this.authRepository = authRepository;
        this.authService = authService;
    }

    @PostMapping("/register")
    public User processRegister(@RequestBody User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());

        user.setPassword(encodedPassword);

        userRepo.save(user);
        this.authRepository.save(new AuthEntity(user.getUsername(), "ROLE_USER"));


        return user;
    }

    @GetMapping("/users")
    public List<User> listUsers() {
        return userRepo.findAll();
    }

    @GetMapping("/current")
    public User getLoggedUser() {
        return this.authService.getAuthUser();
    }

    @GetMapping("/{id}")
    public User getForum(@PathVariable Long id) {
        User user =  userRepo.getUserById(id);

        if(user == null) {
            throw new ResponseStatusException(NOT_FOUND, "Unable to find resource");
        }
        return user;
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        User toUpdate = this.userRepo.getUserById(id);
        AuthEntity authEntity = this.authRepository.getByUsername(toUpdate.getUsername());
        toUpdate.setFirstname(updatedUser.getFirstname());
        toUpdate.setLastname(updatedUser.getLastname());
        toUpdate.setUsername(updatedUser.getUsername());
        toUpdate.setEmail(updatedUser.getEmail());

        toUpdate = this.userRepo.saveAndFlush(toUpdate);
        this.authRepository.save(new AuthEntity(toUpdate.getUsername(), "ROLE_USER"));
        this.authRepository.delete(authEntity);

        return toUpdate;
    }

    @GetMapping("/test")
    public String test() {
        return "<p>Bonjour c'est le endpoint de test JEE (bon y'en as d'autres mais je donne que celui la)&nbsp;" +
                "<br>" +
                "<img src=\"https://en.meming.world/images/en/4/4a/Modern_Problems_Require_Modern_Solutions.jpg\" alt=\"\" /></p>";
    }
}
