package com.example.trunghc.controller;

import com.example.trunghc.dto.request.user.CreateNewUserRequest;
import com.example.trunghc.dto.response.user.GetListUserResponse;
import com.example.trunghc.dto.response.user.GetUserResponse;
import com.example.trunghc.repositories.UserRepository;
import com.example.trunghc.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserController {

    private final UserRepository userRepository;
    private final UserService userService;

    public UserController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @GetMapping("/")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public @ResponseBody
    ResponseEntity<GetListUserResponse> getAllUsers() {
        return userService.getListUser();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('USER')")
    public @ResponseBody
    ResponseEntity<GetUserResponse> getUser(@PathVariable Integer id) {
        return userService.getUser(id);
    }

    @PostMapping("/")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public @ResponseBody
    ResponseEntity<GetUserResponse> addNewUser(@RequestBody CreateNewUserRequest request) {
        return userService.createUser(request);
    }

}
