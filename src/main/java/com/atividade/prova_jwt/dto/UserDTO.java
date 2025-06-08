package com.atividade.prova_jwt.dto;

import com.atividade.prova_jwt.enums.UserRoles;

public class UserDTO {

    private String username;
    private String password;
    private UserRoles role;

    public UserDTO(String username, String password, UserRoles role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public UserRoles getRole() {
        return role;
    }
}
