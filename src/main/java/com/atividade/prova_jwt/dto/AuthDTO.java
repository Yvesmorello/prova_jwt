package com.atividade.prova_jwt.dto;

import com.atividade.prova_jwt.enums.UserRoles;

public class AuthDTO {

    public record AuthRequest(String username, String password, UserRoles role) {}

    public record RegisterRequest(
            String username,
            String password,
            UserRoles role
    ) {}
}
