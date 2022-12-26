package com.yujeans.justdo.user.dto;

import lombok.Data;

@Data
public class CredentialLoginRequestDto {
    private String username;
    private String password;
}