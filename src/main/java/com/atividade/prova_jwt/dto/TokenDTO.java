package com.atividade.prova_jwt.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"message", "token"})
public class TokenDTO {

    private String message;
    private String token;

    public TokenDTO(String token) {
        this.message = "Token para acesso";
        this.token = token;
    }

    public String getMessage() {
        return message;
    }

    public String getToken() {
        return token;
    }
}
