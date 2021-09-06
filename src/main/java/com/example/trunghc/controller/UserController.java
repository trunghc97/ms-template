package com.example.trunghc.controller;

import com.example.trunghc.dto.User;
import com.example.trunghc.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/")
    public @ResponseBody
    Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public @ResponseBody
    Optional<User> getUser(@PathVariable Integer id) {
        return userRepository.findById(id);
    }

    @PostMapping("/")
    public @ResponseBody
    String addNewUser(@RequestParam String name
            , @RequestParam String email) {

        User n = new User();
        n.setName(name);
        n.setEmail(email);
        userRepository.save(n);
        return "Saved";
    }

}
