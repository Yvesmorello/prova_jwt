package com.atividade.prova_jwt.dto;

import com.atividade.prova_jwt.enums.UserRoles;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AdminUserUpdateDTO {
    @NotBlank
    private String username;

    @NotBlank @Email
    private String email;

    @NotBlank
    private String password;

    @NotNull
    private UserRoles role;

}