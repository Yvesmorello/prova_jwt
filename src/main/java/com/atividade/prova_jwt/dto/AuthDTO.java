package com.atividade.prova_jwt.dto;

import com.atividade.prova_jwt.enums.UserRoles;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AuthDTO {

    public record LoginRequest(
            @NotBlank(message = "Username é obrigatório")
            String username,
            @NotBlank(message = "Senha é obrigatória")
            String password
    ) {}

    public record RegisterRequest(
            @NotBlank(message = "Username é obrigatório")
            String username,
            @NotBlank(message = "Email é obrigatório")
            @Email(message = "Email deve ser válido")
            String email,
            @NotBlank(message = "Senha é obrigatória")
            String password,
            @NotNull(message = "Role é obrigatória")
            UserRoles role
    ) {}
}
