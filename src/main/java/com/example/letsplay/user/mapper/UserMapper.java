package com.example.letsplay.user.mapper;

import com.example.letsplay.user.dto.UserRequest;
import com.example.letsplay.user.dto.UserResponse;
import com.example.letsplay.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {
    public UserResponse toResponse(User user) {
        return new UserResponse(user.getName(), user.getRole());
    }

    public User toEntity(UserRequest userRequest) {
        User user = new User();
        user.setName(userRequest.getName());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());
        return user;
    }
}
