package com.example.letsplay.user.controller;

import com.example.letsplay.user.dto.AuthRequest;
import com.example.letsplay.user.dto.AuthResponse;
import com.example.letsplay.user.dto.UserRequest;
import com.example.letsplay.user.dto.UserResponse;
import com.example.letsplay.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public UserResponse register(@RequestBody UserRequest request) {
        return userService.register(request);
    }


    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
//        try {
            AuthResponse response = userService.login(request);
            if (response.getToken().startsWith("Erreur") || response.getToken().contains("invalide")) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            }
            return ResponseEntity.ok(response);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new AuthResponse("Erreur l'ors de la connexion"));
//        }
    }

}
