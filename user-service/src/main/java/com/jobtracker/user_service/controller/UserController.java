package com.jobtracker.user_service.controller;

import org.springframework.web.bind.annotation.*;

import com.jobtracker.user_service.entity.User;
import com.jobtracker.user_service.service.UserService;
import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable("id") Long id) {
        return userService.getUserById(id);
    }

    @PostMapping("/login")
    public User login(@RequestBody User user) {
        return userService.login(
                user.getEmail(),
                user.getPassword());
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

}
