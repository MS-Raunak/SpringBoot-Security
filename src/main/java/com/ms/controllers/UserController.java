package com.ms.controllers;

import com.ms.models.User;
import com.ms.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * For the spring security
 * username : user(by default)
 * password: check your terminal(spring boot generate password)
 */
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;

    //get all user
    @GetMapping("/")
    public List<User> getAllUsers() {
        List<User> users = this.userService.getAllUser();
        return users;
    }

    //get single all user
    @GetMapping("/{username}")
    public User getUser(@PathVariable("username") String name) {
        User user = this.userService.getUserByName(name);
        return user;
    }

    //add new user
    @PostMapping("/")
    public User addUser(@RequestBody User user) {
        User newUser = this.userService.addUser(user);
        return newUser;
    }
}
