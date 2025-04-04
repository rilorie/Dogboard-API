package com.dogboard.dogboard.DTO;

import lombok.Data;

@Data
public class AuthResponseDTO {
    private String accessToken;
    private String tokenType = "Bearer ";
    private Long userId;

    public AuthResponseDTO(String accessToken) {
        this.accessToken = accessToken;
    }
}
