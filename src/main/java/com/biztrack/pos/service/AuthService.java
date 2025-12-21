package com.biztrack.pos.service;

import com.biztrack.pos.exceptions.UserException;
import com.biztrack.pos.payload.dto.UserDto;
import com.biztrack.pos.payload.response.AuthResponse;


public interface AuthService {

    AuthResponse signup(UserDto userDto) throws UserException;
    AuthResponse login(UserDto userDto) throws UserException;
}