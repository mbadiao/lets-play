package com.example.letsplay.user.service;

import com.example.letsplay.exception.BadRequestExeption;
import com.example.letsplay.exception.ResourceNotFoundException;
import com.example.letsplay.exception.UnauthorizedException;
import com.example.letsplay.user.dto.AuthRequest;
import com.example.letsplay.user.dto.AuthResponse;
import com.example.letsplay.user.dto.UserRequest;
import com.example.letsplay.user.dto.UserResponse;
import com.example.letsplay.user.entity.User;
import com.example.letsplay.user.mapper.UserMapper;
import com.example.letsplay.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;



    public List<UserResponse> getAll() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toResponse)
                .toList();
    }

    public UserResponse register(UserRequest userRequest) {
        if (userRepository.existsByEmail(userRequest.getEmail())) {
            throw new BadRequestExeption("Email déjà utilisé", userRequest.getEmail(), userRequest);
        }

        User user = userMapper.toEntity(userRequest);
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user.setRole("USER");

        User savedUser = userRepository.save(user);
        return userMapper.toResponse(savedUser);
    }

    public AuthResponse login(AuthRequest request) {
        try {
            // First authenticate the user
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );

            // If authentication is successful, find the user and generate token
            User user = userRepository.findUserByEmail(request.getEmail())
                    .orElseThrow(() -> new ResourceNotFoundException("Utilisateur introuvable", "email", request.getEmail()));

            String jwt = jwtService.generateToken(user);
            return new AuthResponse(jwt);

        } catch (BadCredentialsException ex) {
            throw new UnauthorizedException("Email ou mot de passe invalide");
        } catch (Exception ex) {
            throw new ResourceNotFoundException("Erreur d'authentification : " + ex.getMessage());
        }
    }
}