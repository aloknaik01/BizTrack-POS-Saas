package com.biztrack.pos.payload.response;


import com.biztrack.pos.payload.dto.UserDto;
import lombok.Data;

@Data
public class AuthResponse {

    private String jwt;
    private String message;
    private UserDto user;
}



