package com.example.letsplay.user.controller;
import com.example.letsplay.user.dto.UserRequest;
import com.example.letsplay.user.dto.UserResponse;
import com.example.letsplay.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
@AllArgsConstructor
public class UserController {
    private final UserService userService;


    @GetMapping
    public List<UserResponse> getAllUsers() {
        return userService.getAll();
    }

//
//    @PostMapping
//    public ResponseEntity<UserResponse> register(@RequestBody UserRequest userRequest) {
//        UserResponse userCreated = userService.register(userRequest);
//        return new ResponseEntity<>(userCreated, HttpStatus.CREATED);
//    }
//
//    @PostMapping
//    public ResponseEntity<> login(@RequestBody UserRequest userRequest) {
//        UserResponse userResponse = userService.login(userRequest);
//    }
}
