package com.example.letsplay.user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AuthRequest {
    @NotBlank(message = "Saisir un  mail correct")
    private String email;

    @NotBlank(message = "Saisir un mot de passe correct")
    private String password;
}
