package com.authentication.controller;

import com.authentication.model.User;
import com.authentication.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

//TODO: create Service implementation to not query Repository directly
@RestController
@RequestMapping("/api")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    //TODO: add error handlings e.g user already exists,
    // tried to delete non-existing user,
    // valid e-mail
    @PostMapping("/user/createOrUpdate/{name}/{password}/{emailAddress}")
    public User createUser(@PathVariable String name,
                           @PathVariable String password,
                           @PathVariable String emailAddress) {
        User user = new User();
        user.setName(name);
        user.setEmailAddress(emailAddress);
        user.setPassword(password);
        return userRepository.save(user);
    }

    //TODO: add confirmation message
    @Transactional
    @PostMapping("/user/delete/{name}")
    public void deleteUser(@PathVariable String name) {
        userRepository.deleteByName(name);
    }

    @Transactional
    @PostMapping("/user/login/{name}/{password}")
    public String login(@PathVariable String name, @PathVariable String password) {
        Optional<User> user = userRepository.findByNameAndPassword(name, password);
        if (user.isEmpty()) {
            return "Could not login";
        } else {
            User loggedUser = user.get();
            loggedUser.setLastLoginDate(new Date());
            userRepository.save(loggedUser);
            return "Welcome!";
        }
    }
}
